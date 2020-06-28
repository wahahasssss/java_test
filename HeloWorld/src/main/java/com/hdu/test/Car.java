package com.hdu.test;

public class Car {
    private String carName;
    private Integer arriveAtTime;
    private Integer chargeType; //1 etc ,2 manual
    private Integer tollgateType; //1 etc, 2 manual,3 mix

    private String tollgateName;

    public Car() {
    }

    public Car(String carName, Integer arriveAtTime, Integer chargeType, Integer tollgateType, String tollgateName) {
        this.carName = carName;
        this.arriveAtTime = arriveAtTime;
        this.chargeType = chargeType;
        this.tollgateType = tollgateType;
        this.tollgateName = tollgateName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public Integer getArriveAtTime() {
        return arriveAtTime;
    }

    public void setArriveAtTime(Integer arriveAtTime) {
        this.arriveAtTime = arriveAtTime;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public Integer getTollgateType() {
        return tollgateType;
    }

    public void setTollgateType(Integer tollgateType) {
        this.tollgateType = tollgateType;
    }


    public String getTollgateName() {
        return tollgateName;
    }

    public void setTollgateName(String tollgateName) {
        this.tollgateName = tollgateName;
    }
}
