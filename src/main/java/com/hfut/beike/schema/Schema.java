package com.hfut.beike.schema;

/**
 * @Classname Schema
 * @Description
 * @Date 2023/1/19 14:13
 * @Created by shukz
 */
public interface Schema {
    Schema formSchema();

    default void UISchema(){};
}
