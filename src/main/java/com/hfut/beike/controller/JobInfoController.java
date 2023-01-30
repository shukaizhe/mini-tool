package com.hfut.beike.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hfut.beike.common.R;
import com.hfut.beike.entity.JobInfo;
import com.hfut.beike.schema.SchemaBus;
import com.hfut.beike.service.JobInfoService;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
    @Resource
    private MongoTemplate mongoTemplate;
    /**
     * 服务对象
     */
    @Resource
    private JobInfoService jobInfoService;

    @Resource
    private SchemaBus schemaBus;

    /**
     * 分页查询所有数据
     *
     * @param page    分页对象
     * @param jobInfo 查询实体
     * @return 所有数据
     */

    @Autowired
    private StringEncryptor encryptor;

    @GetMapping("encode")
    public void encrypt(){
        System.out.println(encryptor.encrypt("2f7a7b86b5f3d99d4d79d6ce8e29eb8f63f724"));
    }

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
    public R<?> selectOne(@PathVariable Serializable id) {
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
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.jobInfoService.removeByIds(idList));
    }

    @GetMapping("/getJobInfo")
    public R<?> getJobInfo() {
        jobInfoService.getJobInfo();
        return R.ok("success");
    }

    @GetMapping("/getJobProperties")
    public R<?> getJobProperties(@RequestParam Integer tableId) {
        JSONObject jsonObject = new JSONObject(true);
        schemaBus.produce(tableId, jsonObject)
                .formSchema()
                .uiSchema()
                .errorSchema();
        jsonObject = JSON.parseObject(jsonObject.toJSONString(), Feature.OrderedField);
//        mongoTemplate.insert(jsonObject,"formSchema");
        return success(jsonObject);
    }
}