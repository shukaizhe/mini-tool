package com.hfut.beike.schema;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

/**
 * @Classname SchemaBus
 * @Description
 * @Date 2023/1/19 14:24
 * @Created by shukz
 */
@Component
public class SchemaBus {
    public Schema produce(Integer tableId, JSONObject json) {
        return new SchemaBuilder(tableId, json);
    }
}
