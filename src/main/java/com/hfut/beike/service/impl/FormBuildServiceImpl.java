package com.hfut.beike.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hfut.beike.dao.FormBuildDao;
import com.hfut.beike.entity.FormBuild;
import com.hfut.beike.service.FormBuildService;
import org.springframework.stereotype.Service;

@Service("formBuildService")
public class FormBuildServiceImpl extends ServiceImpl<FormBuildDao, FormBuild> implements FormBuildService {

}
