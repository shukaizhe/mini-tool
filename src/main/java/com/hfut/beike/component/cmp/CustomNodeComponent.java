package com.hfut.beike.component.cmp;

import com.hfut.beike.component.slot.Context;
import com.yomahub.liteflow.core.NodeComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Classname CustomNodeComponent
 * @Description
 * @Date 2023/2/14 13:59
 * @Created by shukz
 */
public abstract class CustomNodeComponent<C extends Context> extends NodeComponent {

    private final Logger log = LoggerFactory.getLogger(getClass());

    protected C getContext() {
        return this.getContextBean(getContextClazz());
    }

    protected Class<C> getContextClazz() {
        ParameterizedType parameterizedType = getParameterizedType(getClass());
        if (parameterizedType == null) {
            log.error("获取context类型失败");
            throw new RuntimeException("获取context类型失败");
        }
        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        return (Class<C>) typeArguments[0];
    }

    private ParameterizedType getParameterizedType(Class<?> clazz) {
        Type type = clazz.getGenericSuperclass();
        if (type == null) {
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
