package com.hfut.beike.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hfut.beike.common.R;
import com.hfut.beike.entity.JobInfo;
import com.hfut.beike.service.JobInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * 招聘信息(JobInfo)表控制层
 *
 * @author makejava
 * @since 2022-10-19 16:11:20
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("jobInfo")
public class JobInfoController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private JobInfoService jobInfoService;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param jobInfo 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R<?> selectAll(Page<JobInfo> page, JobInfo jobInfo) {
        return success(this.jobInfoService.page(page, new QueryWrapper<>(jobInfo)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?>  selectOne(@PathVariable Serializable id) {
        return success(this.jobInfoService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param jobInfo 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody JobInfo jobInfo) {
        return success(this.jobInfoService.save(jobInfo));
    }

    /**
     * 修改数据
     *
     * @param jobInfo 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody JobInfo jobInfo) {
        return success(this.jobInfoService.updateById(jobInfo));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?>  delete(@RequestParam("idList") List<Long> idList) {
        return success(this.jobInfoService.removeByIds(idList));
    }

    @GetMapping("/getJobInfo")
    public R<?> getJobInfo() {
        jobInfoService.getJobInfo();
        return R.ok("success");
    }

    @GetMapping("/getJobProperties")
    public R<?> getJobProperties() {
        return R.ok(JSON.parse("properties: {\n" +
                "          companyName: {\n" +
                "            type: 'string',\n" +
                "            title: '公司名称',\n" +
                "            default: 'Liu.Jun',\n" +
                "          },\n" +
                "          companyAddr: {\n" +
                "            type: 'string',\n" +
                "            title: '公司联系方式',\n" +
                "            default: '10',\n" +
                "          },\n" +
                "          jobName: {\n" +
                "            type: 'string',\n" +
                "            title: '职位名称',\n" +
                "            minLength: 5\n" +
                "          },\n" +
                "          jobAddr: {\n" +
                "            type: 'string',\n" +
                "            title: '工作地点'\n" +
                "          },\n" +
                "          salary: {\n" +
                "            type: 'string',\n" +
                "            title: '薪资范围',\n" +
                "          },\n" +
                "          url: {\n" +
                "            type: 'string',\n" +
                "            title: '招聘信息详情页'\n" +
                "          },\n" +
                "          time: {\n" +
                "            title: '职位最近发布时间',\n" +
                "            type: 'string',\n" +
                "            format: 'date',\n" +
                "            items: {\n" +
                "              type: 'string'\n" +
                "            },\n" +
                "\n" +
                "          },\n" +
                "          jobDetail: {\n" +
                "            type: 'string',\n" +
                "            title: '职位详情',\n" +
                "            minLength: 1\n" +
                "          }\n" +
                "        }"));
    }
}