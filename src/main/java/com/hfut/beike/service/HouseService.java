package com.hfut.beike.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hfut.beike.entity.House;

/**
 * (House)表服务接口
 *
 * @author makejava
 * @since 2022-10-19 14:52:52
 */
public interface HouseService extends IService<House> {

    void spider();
}

