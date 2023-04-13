package com.hfut.beike.pipeline;

import com.alibaba.fastjson.JSON;
import com.hfut.beike.entity.House;
import com.hfut.beike.service.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
@Slf4j
public class HousePipeline implements Pipeline {

    @Autowired
    private HouseService houseService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的数据
        House house = JSON.parseObject(JSON.toJSONString(resultItems.getAll()), House.class);
        if (house != null) {
            houseService.save(house);
        }
    }
}
