package com.hfut.beike.expection;


/**
 * @Classname ApiErrorCode
 * @Description
 * @Date 2023/1/17 16:42
 * @Created by shukz
 */
public enum ApiErrorCode implements IErrorCode {
    FAILED(-1L, "操作失败"),
    SUCCESS(0L, "执行成功");

    private final long code;
    private final String msg;

    ApiErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ApiErrorCode fromCode(long code) {
        ApiErrorCode[] ecs = values();
        for (ApiErrorCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }

        return SUCCESS;
    }

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", this.code, this.msg);

    }
}
