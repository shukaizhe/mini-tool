package com.hfut.beike;

import com.hfut.beike.component.HouseComponent;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import javax.annotation.Resource;

@SpringBootTest
class HouseApplicationTests {

    @Resource
    HouseComponent houseComponent;

    @Test
    void start() {
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
                    .thread(12)
                    .run();
        }
    }
}
