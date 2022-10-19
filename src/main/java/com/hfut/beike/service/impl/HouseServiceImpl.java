package com.hfut.beike.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hfut.beike.dao.HouseDao;
import com.hfut.beike.entity.House;
import com.hfut.beike.service.HouseService;
import org.springframework.stereotype.Service;

/**
 * (House)表服务实现类
 *
 * @author makejava
 * @since 2022-10-19 14:52:52
 */
@Service("houseService")
public class HouseServiceImpl extends ServiceImpl<HouseDao, House> implements HouseService {

}

