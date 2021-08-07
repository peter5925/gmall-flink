package com.atguigu.gmallpublisher1.service;

import java.math.BigDecimal;
import java.util.Map;

public interface ProductStatsService {

    BigDecimal getGmv(int date);

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
     */
    Map getGmvByTm(int date, int limit);
}
