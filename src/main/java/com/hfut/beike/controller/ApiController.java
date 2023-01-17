package com.hfut.beike.controller;

import com.hfut.beike.common.R;
import com.hfut.beike.expection.IErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Classname ApiController
 * @Description
 * @Date 2023/1/17 16:46
 * @Created by shukz
 */
public abstract class ApiController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ApiController() {
    }

    protected <T> R<T> success(T data) {
        return R.ok(data);
    }

    protected <T> R<T> failed(String msg) {
        return R.failed(msg);
    }

    protected <T> R<T> failed(IErrorCode errorCode) {
        return R.failed(errorCode);
    }
}
