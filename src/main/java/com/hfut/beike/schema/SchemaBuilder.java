package com.hfut.beike.schema;

import com.alibaba.fastjson.JSONObject;
import com.hfut.beike.entity.FormDataPropertiesDTO;
import com.hfut.beike.entity.UIOptionsDTO;

import java.util.*;

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
        formDataPropertiesDTO.setField("companyName");
        formDataPropertiesDTO.setType("string");
        formDataPropertiesDTO.setTitle("公司名称");
        formDataPropertiesDTO.setDefaultValue("Liu.Jun");
        FormDataPropertiesDTO formDataPropertiesDTO1 = new FormDataPropertiesDTO();
        formDataPropertiesDTO.setField("companyAddr");
        formDataPropertiesDTO1.setType("string");
        formDataPropertiesDTO1.setTitle("公司联系方式");
        formDataPropertiesDTO1.setDefaultValue("10");
        FormDataPropertiesDTO formDataPropertiesDTO2 = new FormDataPropertiesDTO();
        formDataPropertiesDTO2.setField("jobName");
        formDataPropertiesDTO1.setType("string");
        formDataPropertiesDTO1.setTitle("职位名称");
        FormDataPropertiesDTO formDataPropertiesDTO3 = new FormDataPropertiesDTO();
        formDataPropertiesDTO2.setField("jobAddr");
        formDataPropertiesDTO1.setType("string");
        formDataPropertiesDTO1.setTitle("工作地点");
        FormDataPropertiesDTO formDataPropertiesDTO4 = new FormDataPropertiesDTO();
        formDataPropertiesDTO2.setField("salary");
        formDataPropertiesDTO1.setType("string");
        formDataPropertiesDTO1.setTitle("薪资范围");
        FormDataPropertiesDTO formDataPropertiesDTO5 = new FormDataPropertiesDTO();
        formDataPropertiesDTO2.setField("url");
        formDataPropertiesDTO1.setType("string");
        formDataPropertiesDTO1.setTitle("招聘信息详情页");
        FormDataPropertiesDTO formDataPropertiesDTO6 = new FormDataPropertiesDTO();
        formDataPropertiesDTO2.setField("jobDetail");
        formDataPropertiesDTO1.setType("string");
        formDataPropertiesDTO1.setTitle("职位详情");
        formProperties.put(formDataPropertiesDTO.getField(), formDataPropertiesDTO);
        formProperties.put(formDataPropertiesDTO1.getField(), formDataPropertiesDTO1);
        formProperties.put(formDataPropertiesDTO2.getField(), formDataPropertiesDTO2);
        formProperties.put(formDataPropertiesDTO3.getField(), formDataPropertiesDTO3);
        formProperties.put(formDataPropertiesDTO4.getField(), formDataPropertiesDTO4);
        formProperties.put(formDataPropertiesDTO5.getField(), formDataPropertiesDTO5);
        formProperties.put(formDataPropertiesDTO6.getField(), formDataPropertiesDTO6);
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