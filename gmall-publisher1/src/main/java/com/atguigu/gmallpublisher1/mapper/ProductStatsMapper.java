package com.atguigu.gmallpublisher1.mapper;

import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

public interface ProductStatsMapper {

    @Select("select sum(order_amount) order_amount from product_stats_210225 where toYYYYMMDD(stt)=#{date}")
    BigDecimal selectGmv(int date);

}
