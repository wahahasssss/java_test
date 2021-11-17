package com.example.springapidemo;

public class CommonResult {

    private int code;
    private String msg;
    private Object data;

    public CommonResult() {

    }

    public CommonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static CommonResult success() {
        return build(0, "success", "");
    }

    public static CommonResult success(Object data) {
        return build(0, "success", data);
    }

//    public static CommonResult success(String msg){
//        return build(0,msg);
//    }

    public static CommonResult success(String msg, Object data) {
        return build(0, msg, data);
    }

    public static CommonResult error() {
        return build(1, "error", "");
    }

    public static CommonResult error(String msg) {
        return build(1, msg);
    }

    public static CommonResult authError(String msg) {
        return build(999, msg);
    }

    public static CommonResult error(String msg, Object data) {
        return build(1, msg, data);
    }

    private static CommonResult build(int code, String msg) {
        return build(code, msg, "");
    }

    private static CommonResult build(int code, String msg, Object data) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(code);
        commonResult.setMsg(msg);
        commonResult.setData(data);
        return commonResult;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }
}
