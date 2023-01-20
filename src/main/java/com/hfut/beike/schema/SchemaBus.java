package com.hfut.beike.schema;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hfut.beike.entity.FormBuild;
import com.hfut.beike.service.FormBuildService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname SchemaBus
 * @Description
 * @Date 2023/1/19 14:24
 * @Created by shukz
 */
@Component
public class SchemaBus {

    @Resource
    private FormBuildService formBuildService;

    public Schema produce(Integer tableId, JSONObject json) {
        LambdaQueryWrapper<FormBuild> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FormBuild::getFormId, tableId);
        List<FormBuild> list = formBuildService.list(queryWrapper);
        return new SchemaBuilder(list, json);
    }
}
