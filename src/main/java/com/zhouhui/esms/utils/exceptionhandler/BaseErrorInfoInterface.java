package com.zhouhui.esms.utils.exceptionhandler;

/**
 * @description: 服务接口类
 * @author zhou
 */
public interface BaseErrorInfoInterface {

    /**
     *  错误码
     * @return
     */
    Integer getResultCode();

    /**
     * 错误描述
     * @return
     */
    String getResultMsg();
}
