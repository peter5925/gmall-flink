package com.atguigu.gmallpublisher1.service.impl;

import com.atguigu.gmallpublisher1.mapper.ProductStatsMapper;
import com.atguigu.gmallpublisher1.service.ProductStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductStatsServiceImpl implements ProductStatsService {

    @Autowired
    private ProductStatsMapper productStatsMapper;

    @Override
    public BigDecimal getGmv(int date) {
        return productStatsMapper.selectGmv(date);
    }

    /**
     * * List{
     * * Map[(tm_name->华为),(order_amount->1200825)],
     * * Map[(tm_name->小米),(order_amount->1049439)],
     * * Map[(tm_name->苹果),(order_amount->1200825)],
     * * Map[(tm_name->TCL),(order_amount->1200825)],
     * * Map[(tm_name->索芙特),(order_amount->1200825)]
     * * }
     * * ==>
     * * Map[(华为->1200825),(小米->1049439)....]
     *
     * @param date
     * @param limit
     * @return
     */
    @Override
    public Map getGmvByTm(int date, int limit) {

        //创建Map用于存放结果数据
        HashMap<String, BigDecimal> resultMap = new HashMap<>();

        //查询ClickHouse获取数据
        List<Map> mapList = productStatsMapper.selectGmvByTm(date, limit);

        //封装
        for (Map map : mapList) {
            resultMap.put((String) map.get("tm_name"), (BigDecimal) map.get("order_amount"));
        }

        //返回数据
        return resultMap;
    }
}
