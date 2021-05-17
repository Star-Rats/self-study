package com.jmy.redislock.service;

import com.jmy.redislock.mapper.PrizeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class PrizeExchangeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private PrizeMapper prizeMapper;
    public Integer prizeExchange(Long prizeId){
        Integer result = 0;
        synchronized (this) {
            result = prizeMapper.reduceStock(prizeId);
        }
        return result;
    }

    public Integer prizeExchangeUseRedis(Long prizeId){
        Integer result = 0;

        // setIfAbsent等价于Jedis中的setnx方法 为Key值设置过期时间为30s
        UUID uuid = new UUID(prizeId, System.currentTimeMillis());
        Boolean lock = stringRedisTemplate.opsForValue().
                setIfAbsent("prizeExchange", uuid.toString(), 30, TimeUnit.SECONDS);
        if (!lock) {
            // 加锁
            stringRedisTemplate.opsForValue().set("prizeExchange",uuid.toString());
            try {
                Timer timer = new Timer(true); // 开启定时器线程为当前线程的守护线程

                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Boolean hasKey = stringRedisTemplate.hasKey("prizeExchange");
                        if (hasKey) {
                            stringRedisTemplate.opsForValue().
                                    set("prizeExchange",uuid.toString(),30, TimeUnit.SECONDS);
                        }
                    }
                }, 1000 * 5L);
                Integer stock = prizeMapper.getPrizeStock(prizeId);
                if (stock > 0) {
                    result = prizeMapper.reduceStock(prizeId);
                }
            } finally {
                // 解锁
                // 定义 lua 脚本
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                // 使用 redis 执行 lua 执行
                DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
                redisScript.setScriptText(script);
                // 设置一下返回值类型 为 Long
                // 因为删除判断的时候，返回的 0,给其封装为数据类型。如果不封装那么默认返回 String 类型，
                // 那么返回字符串与 0 会有发生错误。
                redisScript.setResultType(Long.class);
                // 第一个要是 script 脚本 ，第二个需要判断的 key，第三个就是 key 所对应的值。
                stringRedisTemplate.execute(redisScript, Collections.singletonList("prizeExchange"), uuid.toString());

            }

        }else{
            result = 101; // 用于并发而导致的兑奖失败的返回结果
        }

        return result;
    }
}
