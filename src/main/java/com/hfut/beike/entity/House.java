package com.hfut.beike.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * (House)表实体类
 *
 * @author makejava
 * @since 2022-10-19 14:52:52
 */

@Data
public class House extends Model<House> {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private String state;

    private String model;

    private String address;

    private String type;

    private String area;

    private Double price;

    private String image;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
}

