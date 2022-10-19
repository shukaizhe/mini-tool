package com.hfut.beike.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hfut.beike.entity.JobInfo;

import java.util.List;

/**
 * 招聘信息(JobInfo)表服务接口
 *
 * @author makejava
 * @since 2022-10-19 16:18:02
 */
public interface JobInfoService extends IService<JobInfo> {
    void getJobInfo();
    List<JobInfo> selectJobInfoByUrl(String url);
}

