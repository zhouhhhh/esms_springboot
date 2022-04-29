package com.zhouhui.esms.utils.exceptionhandler;


/**
 * @description: 异常处理枚举类
 * @author zhou
 */

public enum ExceptionEnum implements BaseErrorInfoInterface{


    // 数据操作错误定义
    SUCCESS(2000, "成功!"),
    ERROR(2001, "失败!"),
    BODY_NOT_MATCH(4000,"请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH(4001,"请求的数字签名不匹配!"),
    PARAMS_NOT_CONVERT(4002,"类型转换不对!"),
    NOT_FOUND(4004, "未找到该资源!"),
    INTERNAL_SERVER_ERROR(5000, "服务器内部错误!"),
    SERVER_BUSY(5003,"服务器正忙，请稍后再试!");

    /**
     * 错误码
     */
    private final Integer resultCode;

    /**
     * 错误描述
     */
    private final String resultMsg;

    ExceptionEnum(Integer resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public Integer getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
