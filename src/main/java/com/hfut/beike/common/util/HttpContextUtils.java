package com.hfut.beike.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Http工具类
 * @author zwb
 * @since 2023/2/22 16:04
 */
@Slf4j
public class HttpContextUtils {

    private HttpContextUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取 request 中的请求参数
     * @author zwb
     * @since 2023/2/22 16:04
     * @param request HttpServletRequest
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    public static Map<String, String> getParameterMap(HttpServletRequest request) {
        Enumeration<String> parameters = request.getParameterNames();
        Map<String, String> params = new HashMap<>();
        while (parameters.hasMoreElements()) {
            String parameter = parameters.nextElement();
            String value = request.getParameter(parameter);
            if (StringUtils.isNotBlank(value)) {
                params.put(parameter, value);
            }
        }
        return params;
    }
}