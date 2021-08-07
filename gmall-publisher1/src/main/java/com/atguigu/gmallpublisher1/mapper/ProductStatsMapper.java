package com.atguigu.gmallpublisher1.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ProductStatsMapper {

    @Select("select sum(order_amount) order_amount from product_stats_210225 where toYYYYMMDD(stt)=#{date}")
    BigDecimal selectGmv(int date);

    /**
     * ┌─tm_name─┬─order_amount─┐
     * │ 华为    │   1200825.00 │
     * │ 小米    │   1049439.00 │
     * │ 苹果    │    969474.00 │
     * │ TCL     │    540034.00 │
     * │ 索芙特  │    399951.00 │
     * └─────────┴──────────────┘
     * List{
     * Map[(tm_name->华为),(order_amount->1200825)],
     * Map[(tm_name->小米),(order_amount->1049439)],
     * Map[(tm_name->苹果),(order_amount->1200825)],
     * Map[(tm_name->TCL),(order_amount->1200825)],
     * Map[(tm_name->索芙特),(order_amount->1200825)]
     * }
     * ==>
     * Map[(华为->1200825),(小米->1049439)....]
     *
     * @param date
     * @param limit
     */
    @Select("select tm_name,sum(order_amount) order_amount from product_stats_210225 where toYYYYMMDD(stt)=#{date} group by tm_name order by order_amount desc limit #{limit}")
    List<Map> selectGmvByTm(@Param("date") int date, @Param("limit") int limit);

}
