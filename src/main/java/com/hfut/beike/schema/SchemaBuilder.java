package com.hfut.beike.schema;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hfut.beike.entity.FormBuild;
import com.hfut.beike.entity.Items;
import com.hfut.beike.entity.UIOptionsDTO;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.internal.StringUtil;

import java.util.*;

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
        for (FormBuild formBuild : list) {
            formBuild.setId(null);
            formBuild.setFormId(null);
            if(StringUtils.isBlank(formBuild.getDefaultValue())){
                formBuild.setDefaultValue(null);
            }
            formProperties.put(formBuild.getField(),formBuild);
        }
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
