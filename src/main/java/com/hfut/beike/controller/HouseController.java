package com.hfut.beike.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hfut.beike.common.R;
import com.hfut.beike.entity.House;
import com.hfut.beike.entity.User;
import com.hfut.beike.service.HouseService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (House)表控制层
 *
 * @author makejava
 * @since 2022-10-19 16:25:18
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("house")
public class HouseController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private HouseService houseService;

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 分页查询所有数据
     *
     * @param page  分页对象
     * @param house 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R<?> selectAll(Page<House> page, House house) {
        return success(this.houseService.page(page, new QueryWrapper<>(house)));
    }

    /**
     * 分页查询所有数据
     *
     * @return 所有数据
     */
    @GetMapping("/run")
    public R<?> run() {
        this.houseService.spider();
        return success("ok");
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R<?> selectOne(@PathVariable Serializable id) {
        return success(this.houseService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param house 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R<?> insert(@RequestBody House house) {
        return success(this.houseService.save(house));
    }

    /**
     * 修改数据
     *
     * @param house 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R<?> update(@RequestBody House house) {
        return success(this.houseService.updateById(house));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R<?> delete(@RequestParam("idList") List<Long> idList) {
        return success(this.houseService.removeByIds(idList));
    }

    @PostMapping("/testMongo")
    public R<?> contextLoads(@RequestBody User user) {
        User user1 = mongoTemplate.insert(user);
        return success(user1.getId());
    }
}

