package com.hfut.beike.pipeline;

import com.hfut.beike.entity.JobInfo;
import com.hfut.beike.service.JobInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
@Slf4j
public class MysqlPipeline implements Pipeline {
    @Autowired
    private JobInfoService jobInfoService;

    @Override
    public void process(ResultItems resultItems, Task task) {
        //获取封装好的数据
        JobInfo jobInfo = resultItems.get("jobInfo");
        if (jobInfo != null) {
            jobInfoService.save(jobInfo);
        }
    }
}
