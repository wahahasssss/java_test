package com.hdu.test;

public class ProA {
    private String key1;
    private String key2;
    private String v1;

    public ProA(String key1, String key2, String v1) {

        this.key1 = key1;
        this.key2 = key2;
        this.v1 = v1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }

    public String getV1() {
        return v1;
    }
}
