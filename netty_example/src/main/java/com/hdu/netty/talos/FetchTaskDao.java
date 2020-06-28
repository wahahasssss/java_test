package com.hdu.netty.talos;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/26
 * @Time 下午2:33
 */
public class FetchTaskDao {
    private Integer httpCode;
    private String ip;
    private String dateTime;

    public FetchTaskDao(Integer httpCode, String ip, String dateTime) {
        this.httpCode = httpCode;
        this.ip = ip;
        this.dateTime = dateTime;
    }

    public FetchTaskDao() {
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "FetchTaskDao{" +
                "httpCode=" + httpCode +
                ", ip='" + ip + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
