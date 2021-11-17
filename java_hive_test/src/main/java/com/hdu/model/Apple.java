package com.hdu.model;

import java.awt.*;
import java.io.Serializable;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/19
 * @Time 下午8:58
 */
public class Apple implements Serializable {
    private Double weight;
    private Color color;

    public Apple(Double weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color=" + color +
                '}';
    }
}
