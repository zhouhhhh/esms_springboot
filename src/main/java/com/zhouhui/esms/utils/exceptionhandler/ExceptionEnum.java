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
    NOT_TOKEN(4005, "没有携带token,请重新登录!"),
    NOT_EXIST_USER(4005, "不存在该用户,请重新登录!"),
    TOKEN_VERIFY_ERROR(4005, "token验证失败，请重新登录!"),
    INTERNAL_SERVER_ERROR(5000, "服务器内部错误!"),
    SERVICE_ERROR(5002,"业务逻辑异常!"),
    SERVER_BUSY(5003,"服务器正忙，请稍后再试!"),
    TRANSACTION_ERROR(5008,"事务流异常！");
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
