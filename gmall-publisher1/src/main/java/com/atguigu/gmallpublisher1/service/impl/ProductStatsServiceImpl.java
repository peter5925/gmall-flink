package com.atguigu.gmallpublisher1.service.impl;

import com.atguigu.gmallpublisher1.mapper.ProductStatsMapper;
import com.atguigu.gmallpublisher1.service.ProductStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductStatsServiceImpl implements ProductStatsService {

    @Autowired
    private ProductStatsMapper productStatsMapper;

    @Override
    public BigDecimal getGmv(int date) {
        return productStatsMapper.selectGmv(date);
    }
}
