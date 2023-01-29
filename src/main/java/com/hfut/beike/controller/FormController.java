package com.hfut.beike.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.hfut.beike.common.R;
import com.hfut.beike.expection.ApiErrorCode;
import com.hfut.beike.schema.SchemaBus;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Classname FormController
 * @Description
 * @Date 2023/1/29 16:06
 * @Created by shukz
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("form")
public class FormController extends ApiController {
    @Resource
    private SchemaBus schemaBus;

    @Resource
    private MongoTemplate mongoTemplate;

    @GetMapping("/getForm")
    public R<?> getForm(@RequestParam Integer tableId) {
        JSONObject jsonObject = new JSONObject(true);
        schemaBus.produce(tableId, jsonObject)
                .formSchema()
                .uiSchema()
                .errorSchema();
        jsonObject = JSON.parseObject(jsonObject.toJSONString(), Feature.OrderedField);
        return success(jsonObject);
    }

    @PostMapping("setForm")
    public R<?> setForm(@RequestParam Integer tableId) {
        JSONObject jsonObject = new JSONObject(true);
        schemaBus.produce(tableId, jsonObject)
                .formSchema()
                .uiSchema()
                .errorSchema();
        jsonObject = JSON.parseObject(jsonObject.toJSONString(), Feature.OrderedField);
        mongoTemplate.insert(jsonObject,"formSchema");
        return success(ApiErrorCode.SUCCESS);
    }
}
