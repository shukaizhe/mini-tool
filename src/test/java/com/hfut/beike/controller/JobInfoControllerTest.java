package com.hfut.beike.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hfut.beike.common.R;
import com.hfut.beike.entity.JobInfo;
import com.hfut.beike.schema.SchemaBus;
import com.hfut.beike.service.JobInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.Serializable;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JobInfoControllerTest {

    @Mock
    private MongoTemplate mockMongoTemplate;
    @Mock
    private JobInfoService mockJobInfoService;
    @Mock
    private SchemaBus mockSchemaBus;

    @InjectMocks
    private JobInfoController jobInfoControllerUnderTest;

    @Test
    public void testSelectAll() {
        // Setup
        final Page<JobInfo> page = new Page<>(0L, 0L, 0L, false);
        final JobInfo jobInfo = new JobInfo();
        jobInfo.setId(0L);
        jobInfo.setCompanyName("companyName");
        jobInfo.setCompanyAddr("companyAddr");
        jobInfo.setJobName("jobName");
        jobInfo.setJobAddr("jobAddr");
        jobInfo.setSalary("salary");
        jobInfo.setUrl("url");
        jobInfo.setTime("time");
        jobInfo.setJobDetail("jobDetail");

        when(mockJobInfoService.page(any(Page.class), any(QueryWrapper.class)))
                .thenReturn(new Page<>(0L, 0L, 0L, false));

        // Run the test
        final R<?> result = jobInfoControllerUnderTest.selectAll(page, jobInfo);

        // Verify the results
    }

    @Test
    public void testSelectOne() {
        // Setup
        // Configure JobInfoService.getById(...).
        final JobInfo jobInfo = new JobInfo();
        jobInfo.setId(0L);
        jobInfo.setCompanyName("companyName");
        jobInfo.setCompanyAddr("companyAddr");
        jobInfo.setJobName("jobName");
        jobInfo.setJobAddr("jobAddr");
        jobInfo.setSalary("salary");
        jobInfo.setUrl("url");
        jobInfo.setTime("time");
        jobInfo.setJobDetail("jobDetail");
        when(mockJobInfoService.getById(any(Serializable.class))).thenReturn(jobInfo);

        // Run the test
        final R<?> result = jobInfoControllerUnderTest.selectOne("value");

        // Verify the results
    }

    @Test
    public void testInsert() {
        // Setup
        final JobInfo jobInfo = new JobInfo();
        jobInfo.setId(0L);
        jobInfo.setCompanyName("companyName");
        jobInfo.setCompanyAddr("companyAddr");
        jobInfo.setJobName("jobName");
        jobInfo.setJobAddr("jobAddr");
        jobInfo.setSalary("salary");
        jobInfo.setUrl("url");
        jobInfo.setTime("time");
        jobInfo.setJobDetail("jobDetail");

        when(mockJobInfoService.save(new JobInfo())).thenReturn(false);

        // Run the test
        final R<?> result = jobInfoControllerUnderTest.insert(jobInfo);

        // Verify the results
    }

    @Test
    public void testUpdate() {
        // Setup
        final JobInfo jobInfo = new JobInfo();
        jobInfo.setId(0L);
        jobInfo.setCompanyName("companyName");
        jobInfo.setCompanyAddr("companyAddr");
        jobInfo.setJobName("jobName");
        jobInfo.setJobAddr("jobAddr");
        jobInfo.setSalary("salary");
        jobInfo.setUrl("url");
        jobInfo.setTime("time");
        jobInfo.setJobDetail("jobDetail");

        when(mockJobInfoService.updateById(new JobInfo())).thenReturn(false);

        // Run the test
        final R<?> result = jobInfoControllerUnderTest.update(jobInfo);

        // Verify the results
    }

    @Test
    public void testDelete() {
        // Setup
        when(mockJobInfoService.removeByIds(Arrays.asList("value"))).thenReturn(false);

        // Run the test
        final R<?> result = jobInfoControllerUnderTest.delete(Arrays.asList(0L));

        // Verify the results
    }

    @Test
    public void testGetJobInfo() {
        // Setup
        // Run the test
        final R<?> result = jobInfoControllerUnderTest.getJobInfo();

        // Verify the results
        verify(mockJobInfoService).getJobInfo();
    }

//    @Test
//    public void testGetJobProperties() {
//        // Setup
//        when(mockSchemaBus.produce(0, new JSONObject(0, false))).thenReturn(null);
//
//        // Run the test
//        final R<?> result = jobInfoControllerUnderTest.getJobProperties(0);
//
//        // Verify the results
//    }
}
