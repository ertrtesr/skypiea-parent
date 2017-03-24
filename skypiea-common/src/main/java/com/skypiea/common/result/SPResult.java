package com.skypiea.common.result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skypiea.common.http.HttpMsg;
import com.skypiea.common.http.HttpStatus;

import java.io.IOException;
import java.util.List;

/**
 * 作者: huangwenjian
 * 描述: 通用的pojo包装类
 * 创建时间: 2017-02-20 18:55
 */
public class SPResult {

    //定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private int status;     //响应业务状态
    private String msg;     //响应消息
    private Object data;    //响应的数据

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public SPResult() {
    }

    public SPResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public SPResult(int status, String msg, Object data) {
        this(status, msg);
        this.data = data;
    }

    public static SPResult build(int status, String msg, Object data) {
        return new SPResult(status, msg, data);
    }

    public static SPResult build(int status, String msg) {
        return build(status, msg, null);
    }

    public static SPResult ok(Object data) {
        return new SPResult(HttpStatus.OK, HttpMsg.OK, data);
    }

    public static SPResult ok() {
        return ok(null);
    }

    //用于客户端请求错误返回
    public static SPResult fail(Object data) {
        return new SPResult(HttpStatus.BadRequest, HttpMsg.FAIL, data);
    }

    //用户客户端错误
    public static SPResult fail() {
        return fail(null);
    }

    //用于服务端错误
    public static SPResult error(Object data) {
        return new SPResult(HttpStatus.InternalServerError, HttpMsg.ERROR, data);
    }

    public static SPResult error() {
        return error(null);
    }

    public static SPResult formatToObject(String json, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(json, SPResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(json);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SPResult format(String json) {
        try {
            return MAPPER.readValue(json, SPResult.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static SPResult formatToList(String json, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(json);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("data").asText(), obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
