package com.jmy.redislock.redis;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testPrizeExchangeUseRedis(){
        stringRedisTemplate.opsForValue().set("stock","10");
        System.out.println(stringRedisTemplate.opsForValue().get("stock"));
    }
}
