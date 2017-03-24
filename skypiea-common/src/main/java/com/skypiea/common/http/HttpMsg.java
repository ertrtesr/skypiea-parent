package com.skypiea.common.http;

/**
 * 作者: huangwenjian
 * 描述: 响应的消息
 * 创建时间: 2017-03-21 22:36
 */
public interface HttpMsg {
    String OK = "ok";           //请求成功
    String FAIL = "fail";       //客户端请求失败返回
    String ERROR = "error";     //服务端错误
}
