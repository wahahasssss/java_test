package com.hdu.model.enums;

import com.hdu.operations.OpEQ;
import com.hdu.operations.SimpleOperator;

public enum ExpressionEnum {
    LT(0, "<"),
    LE(1, "<="),
    EQ(2, "=="),
    GT(3, ">"),
    GE(4, ">="),
    UNKNOWN(-1, "unknown");
    private Integer index;
    private String express;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    ExpressionEnum(Integer index, String desc) {
        this.index = index;
        this.express = desc;
    }

    public static ExpressionEnum valueOfExpress(String exp) {
        switch (exp) {
            case "<":
                return LT;
            case "<=":
                return LE;
            case "==":
                return EQ;
            case ">":
                return GT;
            case ">=":
                return GE;
        }
        return UNKNOWN;
    }


}
