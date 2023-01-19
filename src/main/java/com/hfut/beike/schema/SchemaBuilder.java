package com.hfut.beike.schema;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hfut.beike.entity.FormBuild;
import com.hfut.beike.entity.Items;
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

    public SchemaBuilder(Integer tableId, JSONObject json) {
        this.tableId = tableId;
        this.json = json;
    }

    @Override
    public Schema formSchema() {
        Map<String, FormBuild> formProperties = new HashMap<>();
        FormBuild formDataPropertiesDTO = new FormBuild();
        formDataPropertiesDTO.setField("companyName");
        formDataPropertiesDTO.setType("string");
        formDataPropertiesDTO.setTitle("公司名称");
        formDataPropertiesDTO.setDefaultValue("Liu.Jun");
        FormBuild formDataPropertiesDTO1 = new FormBuild();
        formDataPropertiesDTO1.setField("companyAddr");
        formDataPropertiesDTO1.setType("string");
        formDataPropertiesDTO1.setTitle("公司联系方式");
        formDataPropertiesDTO1.setDefaultValue("10");
        FormBuild formDataPropertiesDTO2 = new FormBuild();
        formDataPropertiesDTO2.setField("jobName");
        formDataPropertiesDTO2.setType("string");
        formDataPropertiesDTO2.setTitle("职位名称");
        FormBuild formDataPropertiesDTO3 = new FormBuild();
        formDataPropertiesDTO3.setField("jobAddr");
        formDataPropertiesDTO3.setType("string");
        formDataPropertiesDTO3.setTitle("工作地点");
        FormBuild formDataPropertiesDTO4 = new FormBuild();
        formDataPropertiesDTO4.setField("salary");
        formDataPropertiesDTO4.setType("string");
        formDataPropertiesDTO4.setTitle("薪资范围");
        FormBuild formDataPropertiesDTO5 = new FormBuild();
        formDataPropertiesDTO5.setField("url");
        formDataPropertiesDTO5.setType("string");
        formDataPropertiesDTO5.setTitle("招聘信息详情页");
        FormBuild formDataPropertiesDTO6 = new FormBuild();
        formDataPropertiesDTO6.setField("jobDetail");
        formDataPropertiesDTO6.setType("string");
        formDataPropertiesDTO6.setTitle("职位详情");
        FormBuild formDataPropertiesDTO7 = new FormBuild();
        formDataPropertiesDTO7.setField("time");
        formDataPropertiesDTO7.setType("string");
        formDataPropertiesDTO7.setTitle("工作时间");
        formDataPropertiesDTO7.setFormat("date");
        Items items = new Items();
        items.setType("string");
        formDataPropertiesDTO7.setItems(JSON.parseObject("{'type':'string'}",Items.class));
        formProperties.put(formDataPropertiesDTO.getField(), formDataPropertiesDTO);
        formProperties.put(formDataPropertiesDTO1.getField(), formDataPropertiesDTO1);
        formProperties.put(formDataPropertiesDTO2.getField(), formDataPropertiesDTO2);
        formProperties.put(formDataPropertiesDTO3.getField(), formDataPropertiesDTO3);
        formProperties.put(formDataPropertiesDTO4.getField(), formDataPropertiesDTO4);
        formProperties.put(formDataPropertiesDTO5.getField(), formDataPropertiesDTO5);
        formProperties.put(formDataPropertiesDTO6.getField(), formDataPropertiesDTO6);
        formProperties.put(formDataPropertiesDTO7.getField(), formDataPropertiesDTO7);
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
        uiMap.put("ui:options", uiOptionsDTO);
        Map<String, Map<String, UIOptionsDTO>> uiSchema = new HashMap<>();
        uiSchema.put("companyName", uiMap);
        json.put("uiSchema", uiSchema);
        return this;
    }
}
