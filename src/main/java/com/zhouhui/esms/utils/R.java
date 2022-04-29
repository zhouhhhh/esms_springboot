package com.zhouhui.esms.utils;

import com.zhouhui.esms.utils.exceptionhandler.BaseErrorInfoInterface;
import com.zhouhui.esms.utils.exceptionhandler.ExceptionEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


/**
 * 统一结果返回类
 *
 * @author zhou
 */
@Data
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")

    private Map<String, Object> data = new HashMap<String, Object>();

    public R() {
    }

    public R(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }

    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ExceptionEnum.SUCCESS.getResultCode());
        r.setMessage(ExceptionEnum.SUCCESS.getResultMsg());
        return r;

    }

    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ExceptionEnum.ERROR.getResultCode());
        r.setMessage(ExceptionEnum.ERROR.getResultMsg());
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

    /**
     * 失败
     */
    public static R error(BaseErrorInfoInterface errorInfo) {
        R rb = new R();
        rb.setSuccess(false);
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        return rb;
    }

    /**
     * 失败
     */
    public static R error(Integer code, String message) {
        R rb = new R();
        rb.setSuccess(false);
        rb.setCode(code);
        rb.setMessage(message);
        return rb;
    }

    /**
     * 失败
     */
    public static R error( String message) {
        R rb = new R();
        rb.setSuccess(false);
        rb.setCode(-1);
        rb.setMessage(message);
        return rb;
    }

}