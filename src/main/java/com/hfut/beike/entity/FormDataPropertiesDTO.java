package com.hfut.beike.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @Classname FormDataPropertiesDTO
 * @Description
 * @Date 2023/1/19 10:23
 * @Created by shukz
 */
@Data
public class FormDataPropertiesDTO {
    private String field;
    private String type;
    private String title;
    // 与java关键字重名
    @JSONField(name = "default")
    private String defaultValue;
}
