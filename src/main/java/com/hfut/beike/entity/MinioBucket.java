package com.hfut.beike.entity;

import io.minio.messages.ResponseDate;
import lombok.Data;
import org.simpleframework.xml.Element;

import java.time.ZonedDateTime;

/**
 * @Classname MinioBucket
 * @Description
 * @Date 2023/1/29 10:01
 * @Created by shukz
 */
@Data
public class MinioBucket {
    private String name;
    private ZonedDateTime zonedDateTime;
}
