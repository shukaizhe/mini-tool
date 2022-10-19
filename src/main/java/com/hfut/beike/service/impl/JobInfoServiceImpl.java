package com.hfut.beike.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hfut.beike.dao.JobInfoDao;
import com.hfut.beike.entity.JobInfo;
import com.hfut.beike.pipeline.MysqlPipeline;
import com.hfut.beike.processor.JobProcessor;
import com.hfut.beike.service.JobInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.QueueScheduler;

import java.util.List;

/**
 * 招聘信息(JobInfo)表服务实现类
 *
 * @author makejava
 * @since 2022-10-19 16:11:20
 */
@Service("jobInfoService")
@Slf4j
public class JobInfoServiceImpl extends ServiceImpl<JobInfoDao, JobInfo> implements JobInfoService {
    //开始爬取的url
    String url = "https://search.51job.com/list/000000,000000,0000,00,9,99,java,2,1.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare=";

    @Autowired
    private MysqlPipeline mysqlPipeline;

    @Autowired
    private JobProcessor jobProcessor;

    @Override
    public void getJobInfo() {
        log.info("开始爬取数据");
        //设置爬虫配置
        Spider.create(jobProcessor)
                .addUrl(url) //设置初始爬取的url
                //使用布隆过滤器过滤重复url,需要引入guava包
                .setScheduler(new QueueScheduler().setDuplicateRemover(new BloomFilterDuplicateRemover(100000)))
                .thread(50) //设置线程数
                .addPipeline(mysqlPipeline) //设置持久化
                .run();
    }

    @Override
    public List<JobInfo> selectJobInfoByUrl(String url) {
        QueryWrapper<JobInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("url", url);
        List<JobInfo> jobInfos = this.baseMapper.selectList(wrapper);
        return jobInfos;
    }
}

