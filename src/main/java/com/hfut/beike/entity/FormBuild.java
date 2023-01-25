package com.hfut.beike.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;

/**
 * @Classname FormDataPropertiesDTO
 * @Description
 * @Date 2023/1/19 10:23
 * @Created by shukz
 */
@Data
@TableName(autoResultMap = true)
public class FormBuild {
    private Long id;
    private Long formId;
    private String field;
    private String type;
    private String title;
    private String isRequired;
    private String format;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private Items items;
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private UIOptions uiOptions;
    // 与java关键字重名
    @JSONField(name = "default")
    private String defaultValue;
}
