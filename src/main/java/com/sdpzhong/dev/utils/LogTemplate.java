package com.sdpzhong.dev.utils;

import java.util.Map;

/**
 * @Author: zhongqing
 * @Description: log 文件存储
 * @Date: 2024-07-08 17:50
 **/

public class LogTemplate {

    /**
     * 生成日志模板
     *
     * @param origin Map<String, Object> 模板数据
     * @return template 字符串模板
     */
    public static String createLogTemplate(Map<String, Object> origin) {
        StringBuilder template = new StringBuilder("========================================================================>\n");
        for (String key : origin.keySet()) {
            template.append("* ").append(capitalizeFirstLetter(key)).append(": ").append(origin.get(key)).append("\n");
        }
        template.append("========================================================================>");
        return template.toString();
    }

    public static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }
}
