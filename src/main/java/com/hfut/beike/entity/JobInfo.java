package com.hfut.beike.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 招聘信息(JobInfo)表实体类
 *
 * @author makejava
 * @since 2022-10-19 16:11:20
 */
@Data
@TableName("job_info")
@Slf4j
public class JobInfo extends Model<JobInfo> {
    //主键id
    @TableId(type = IdType.AUTO)
    private Long id;
    //公司名称
    private String companyName;
    //公司联系方式
    private String companyAddr;
    //职位名称
    private String jobName;
    //工作地点
    private String jobAddr;
    //薪资范围
    private String salary;
    //招聘信息详情页
    private String url;
    //职位最近发布时间
    private String time;
    //职位详情
    private String jobDetail;
}

