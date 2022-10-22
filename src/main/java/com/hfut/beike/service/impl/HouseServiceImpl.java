package com.hfut.beike.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hfut.beike.component.HouseComponent;
import com.hfut.beike.dao.HouseDao;
import com.hfut.beike.entity.House;
import com.hfut.beike.pipeline.HousePipeline;
import com.hfut.beike.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

/**
 * (House)表服务实现类
 *
 * @author makejava
 * @since 2022-10-19 14:52:52
 */
@Service("houseService")
public class HouseServiceImpl extends ServiceImpl<HouseDao, House> implements HouseService {

    @Autowired
    HouseComponent houseComponent;

    @Autowired
    HousePipeline housePipeline;

    @Scheduled(cron = "0 0 10,14,16 * * ?")
    @Override
    public void spider() {
        String[] cities = {"bj", "sh", "gz", "sz", "cc", "cd", "cq", "cs", "dl", "fz", "gy", "hhht", "hrb",
                "hf", "hz", "hk", "jn", "km", "lasa", "lz", "nj", "nb", "nc", "nn", "qd", "su", "sy", "sjz",
                "tj", "ty", "wh", "wlmq", "xm", "xa", "xining", "yinchuan", "zz"};
        for (String city : cities) {
            String url = "https://" + city + ".fang.ke.com/loupan/nha3l2nhd2/";
            // 使用布隆过滤器
            BloomFilterDuplicateRemover bloomFilter =
                    new BloomFilterDuplicateRemover(100 * 1000);
            Spider.create(houseComponent)
                    .addUrl(url)
                    .setScheduler(new QueueScheduler().setDuplicateRemover(bloomFilter))
                    .addPipeline(housePipeline)
                    .thread(12)
                    .run();
        }
    }
}

