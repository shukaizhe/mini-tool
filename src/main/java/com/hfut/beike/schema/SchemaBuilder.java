package com.hfut.beike.schema;

import com.alibaba.fastjson.JSONObject;
import com.hfut.beike.entity.FormBuild;
import com.hfut.beike.entity.UIOptions;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Classname SchemaBuilder
 * @Description
 * @Date 2023/1/19 14:15
 * @Created by shukz
 */
public class SchemaBuilder implements Schema {

    private final List<FormBuild> list;

    private final JSONObject json;

    public SchemaBuilder(List<FormBuild> list, JSONObject json) {
        this.list = list;
        this.json = json;
    }

    @Override
    public Schema formSchema() {
        Map<String, FormBuild> formProperties = new LinkedHashMap<>();
        List<String> required = list.stream()
                .filter(d -> d.getIsRequired().equals("Y"))
                .map(FormBuild::getField)
                .collect(Collectors.toList());
        list.forEach((FormBuild formBuild) -> {
            formBuild.setId(null);
            formBuild.setFormId(null);
            formBuild.setIsRequired(null);
            if (StringUtils.isBlank(formBuild.getDefaultValue())) {
                formBuild.setDefaultValue(null);
            }
            formProperties.put(formBuild.getField(), formBuild);
        });
        json.put("type", "object");
        json.put("required", required);
        json.put("properties", formProperties);
        return this;
    }

    @Override
    public Schema uiSchema() {
        Map<String, Map<String, UIOptions>> uiSchema = new HashMap<>();
        List<FormBuild> formBuilds = list.parallelStream()
                .filter(d -> ObjectUtils.isNotEmpty(d.getUiOptions()))
                        .collect(Collectors.toList());
        formBuilds.forEach((FormBuild formBuild) -> {
            Map<String, UIOptions> uiMap = new HashMap<>();
            uiMap.put("ui:options", formBuild.getUiOptions());
            uiSchema.put(formBuild.getField(), uiMap);
        });
        json.put("uiSchema", uiSchema);
        return this;
    }

    @Override
    public void errorSchema() {
        json.put("errorSchema", null);
    }
}
