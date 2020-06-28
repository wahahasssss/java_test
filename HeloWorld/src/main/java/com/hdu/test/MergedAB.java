package com.hdu.test;

public class MergedAB {
    private String key1;
    private String key2;
    private String v1;
    private String v2;

    public MergedAB(String key1, String key2, String v1, String v2) {
        this.key1 = key1;
        this.key2 = key2;
        this.v1 = v1;
        this.v2 = v2;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public String getKey2() {
        return key2;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    @Override
    public String toString() {
        return "MergedAB{" +
                "key1='" + key1 + '\'' +
                ", key2='" + key2 + '\'' +
                ", v1='" + v1 + '\'' +
                ", v2='" + v2 + '\'' +
                '}';
    }
}
