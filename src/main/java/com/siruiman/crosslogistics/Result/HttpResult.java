package com.siruiman.crosslogistics.Result;

public class HttpResult {

    // 响应的状态码
    private Integer code;

    // 响应的响应体
    private String body;

    private Object data;

    public HttpResult(int code, String body) {
        this.code = code;
        this.body = body;
    }

    public HttpResult(Integer code, String body, Object data) {
        this.code = code;
        this.body = body;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
