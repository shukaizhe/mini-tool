package com.hfut.beike.common.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反着工具类
 * @author zwb
 * @since 2023/2/20 19:11
 */
public class ReflectionUtil {

    private ReflectionUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取泛型父类 ParameterizedType
     * @author zwb
     * @since 2022/11/8 11:20
     * @param clazz 类
     * @return java.lang.reflect.ParameterizedType
     */
    public static ParameterizedType getParameterizedType(Class<?> clazz){
        Type type = clazz.getGenericSuperclass();
        if (type == null){
            return null;
        }
        ParameterizedType parameterizedType = null;
        if (type instanceof Class) {
            parameterizedType = getParameterizedType((Class<?>) type);
        } else if (type instanceof ParameterizedType) {
            parameterizedType = (ParameterizedType) type;
        }
        return parameterizedType;
    }
}
