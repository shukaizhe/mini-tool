package com.hfut.beike.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 全局的isDelete枚举
 *
 * @author qianyuqi
 * @since 2022-03-09 14:06
 */
@Getter
@AllArgsConstructor
public enum GlobalDeleteEnum {
    /**
     * 0正常-未删除
     */
    NORMAL(0),

    /**
     * 1-已删除
     */
    INVALID(1);

    private final int code;
}
