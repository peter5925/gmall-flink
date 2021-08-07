package com.atguigu.gmallpublisher1.controller;

import com.atguigu.gmallpublisher1.service.ProductStatsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@RestController
public class SugarController {

    @Autowired
    private ProductStatsService productStatsService;

    /**
     * {
     * "status": 0,
     * "msg": "",
     * "data": 1201093.6095063353
     * }
     */
    @RequestMapping("/api/sugar/gmv")
    public String getGmv(@RequestParam(value = "date", defaultValue = "0") int date) {

        if (date == 0) {
            date = getToday();
            System.out.println(date);
        }

        return "        {" +
                "          \"status\": 0," +
                "          \"msg\": \"\"," +
                "          \"data\": " + productStatsService.getGmv(date) + "" +
                "        }";
    }

    @RequestMapping("/api/sugar/tm")
    public String getGmvByTm(@RequestParam(value = "date", defaultValue = "0") int date,
                             @RequestParam("limit") int limit) {

        if (date == 0) {
            date = getToday();
        }

        //查询ClickHouse数据
        Map gmvByTm = productStatsService.getGmvByTm(date, limit);

        Set tmNames = gmvByTm.keySet();
        Collection values = gmvByTm.values();

        //封装字符串输出
        return "{" +
                "  \"status\": 0," +
                "  \"msg\": \"\"," +
                "  \"data\": {" +
                "    \"categories\": [\"" +
                StringUtils.join(tmNames, "\",\"") +
                "    \"]," +
                "    \"series\": [" +
                "      {" +
                "        \"name\": \"品牌\"," +
                "        \"data\": [" +
                StringUtils.join(values, ",") +
                "        ]" +
                "      }" +
                "    ]" +
                "  }" +
                "}";
    }

    private int getToday() {
        long ts = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateTime = sdf.format(ts);
        return Integer.parseInt(dateTime);
    }

}
