package com.example.shopping.utils;

import com.alibaba.fastjson.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author: 叶莹
 * Create at: 2018/8/2
 **/
public class CommonUtilTools {

    /**
     * @param value
     * @param defaultValue
     * @return
     */
    public static Object setDefaultValue(Object value, Object defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static String returnFailResponse(String errorMsg) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        result.put("error", errorMsg);
        return result.toJSONString();
    }

    public static String returnFailResponse(Exception event) {
        return CommonUtilTools.returnFailResponse(event.toString());
    }

    public static String returnSuccessResponse(Object object) {
        JSONObject response = new JSONObject();
        response.put("success", true);
        response.put("result", object);
        return response.toString();
    }

    // 返回只精确到日的日期格式
    public static String getSimpleDateFormat(Date date) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

}
