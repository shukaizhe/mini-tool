package com.hfut.beike.schema;

import com.alibaba.fastjson.JSONObject;
import com.hfut.beike.entity.FormDataPropertiesDTO;
import com.hfut.beike.entity.UIOptionsDTO;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname SchemaBuilder
 * @Description
 * @Date 2023/1/19 14:15
 * @Created by shukz
 */
public class SchemaBuilder implements Schema {

    private final Integer tableId;

    private final JSONObject json;

    public SchemaBuilder(Integer tableId,JSONObject json) {
        this.tableId = tableId;
        this.json = json;
    }

    @Override
    public Schema formSchema() {
        Map<String, FormDataPropertiesDTO> formProperties = new HashMap<>();
        FormDataPropertiesDTO formDataPropertiesDTO = new FormDataPropertiesDTO();
        formDataPropertiesDTO.setType("string");
        formDataPropertiesDTO.setTitle("公司名称");
        formDataPropertiesDTO.setDefaultValue("Liu.Jun");
        FormDataPropertiesDTO formDataPropertiesDTO1 = new FormDataPropertiesDTO();
        formDataPropertiesDTO1.setType("string");
        formDataPropertiesDTO1.setTitle("公司联系方式");
        formDataPropertiesDTO1.setDefaultValue("10");
        formProperties.put("companyName", formDataPropertiesDTO);
        formProperties.put("companyAddr", formDataPropertiesDTO1);
        json.put("type", "object");
        json.put("required", Arrays.asList("companyName", "companyAddr"));
        json.put("properties", formProperties);
        return this;
    }

    @Override
    public Schema UISchema() {
        UIOptionsDTO uiOptionsDTO = new UIOptionsDTO();
        uiOptionsDTO.setType("textarea");
        uiOptionsDTO.setPlaceholder("请输入职位详情");
        uiOptionsDTO.setRows(5);
        Map<String, UIOptionsDTO> uiMap = new HashMap<>();
        uiMap.put("ui:options",uiOptionsDTO);
        Map<String,Map<String, UIOptionsDTO>> uiSchema = new HashMap<>();
        uiSchema.put("companyName",uiMap);
        json.put("uiSchema", uiSchema);
        return this;
    }
}
