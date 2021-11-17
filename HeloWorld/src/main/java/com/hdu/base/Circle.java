package com.hdu.base;

public class Circle implements ICallulate {
    public float getArea(float r) {
        return r * r;
    }

    public float getCircumference(float r) {
        return r * r * r;
    }
}
