package com.hdu.handler;

import java.util.HashMap;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/17
 * @Time 下午2:57
 */
public class ResponseModel {
    private Integer code;
    private HashMap<String,String> headers;

    public ResponseModel() {
    }

    public ResponseModel(Integer code, HashMap<String, String> headers) {
        this.code = code;
        this.headers = headers;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }
}
