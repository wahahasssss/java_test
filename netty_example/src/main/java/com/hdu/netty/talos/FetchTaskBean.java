package com.hdu.netty.talos;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/26
 * @Time 下午2:36
 */
public class FetchTaskBean {
    private Integer httpCode;
    private String ip;
    private Long timeStamp;

    public FetchTaskBean() {
    }

    public FetchTaskBean(Integer httpCode, String ip, Long timeStamp) {
        this.httpCode = httpCode;
        this.ip = ip;
        this.timeStamp = timeStamp;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(Integer httpCode) {
        this.httpCode = httpCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "FetchTaskBean{" +
                "httpCode=" + httpCode +
                ", ip='" + ip + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
