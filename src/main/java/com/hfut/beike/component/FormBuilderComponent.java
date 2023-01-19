package com.hfut.beike.component;

import com.alibaba.fastjson.JSONObject;
import com.hfut.beike.entity.FormDataPropertiesDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname FormBuilderCompent
 * @Description
 * @Date 2023/1/19 10:16
 * @Created by shukz
 */
@Component
public class FormBuilderComponent {

    public void schemaBuilder(JSONObject json) {
        Map<String, FormDataPropertiesDTO> formProperties = new HashMap<>();
        FormDataPropertiesDTO formDataPropertiesDTO = new FormDataPropertiesDTO();
        formDataPropertiesDTO.setType("string");
        formDataPropertiesDTO.setTitle("公司名称");
        formDataPropertiesDTO.setDefaultValue("Liu.Jun");
        FormDataPropertiesDTO formDataPropertiesDTO1 = new FormDataPropertiesDTO();
        formDataPropertiesDTO1.setType("number");
        formDataPropertiesDTO1.setTitle("公司联系方式");
        formDataPropertiesDTO1.setDefaultValue("10");
        formProperties.put("companyName", formDataPropertiesDTO);
        formProperties.put("companyAddr",formDataPropertiesDTO1);
        json.put("type", "object");
        json.put("required", Arrays.asList("companyName", "companyAddr"));
        json.put("properties", formProperties);
    }

    public void uiSchemaBuilder(JSONObject json) {

    }
}
