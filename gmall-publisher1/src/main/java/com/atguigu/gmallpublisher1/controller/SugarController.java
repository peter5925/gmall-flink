package com.atguigu.gmallpublisher1.controller;

import com.atguigu.gmallpublisher1.service.ProductStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;

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

    private int getToday() {
        long ts = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateTime = sdf.format(ts);
        return Integer.parseInt(dateTime);
    }

}
