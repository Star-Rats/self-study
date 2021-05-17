package com.jmy.redislock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PrizeMapper {

    @Update("update prize set stock = stock-1 where id=#{prizeId}")
    Integer reduceStock(Long prizeId);

    @Select("select stock from prize where id = #{id}")
    Integer getPrizeStock(Long id);
}
