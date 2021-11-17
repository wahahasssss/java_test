package com.hdu.operations;

import org.springframework.util.NumberUtils;

import java.math.BigDecimal;

/**
 * @ClassName：ObjectComparator
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/9/7 2:41 下午
 * @Versiion：1.0
 */
public class ObjectComparator implements SimpleComparator {
    @Override
    public int comparator(Object leftValue, Object rightValue) {
        if (leftValue instanceof Number && rightValue instanceof Number) {
            Number leftNumber = (Number) leftValue;
            Number rightNumber = (Number) rightValue;
            if (leftNumber instanceof BigDecimal && rightNumber instanceof BigDecimal) {
                BigDecimal leftBigDecimal = NumberUtils.convertNumberToTargetClass(leftNumber, BigDecimal.class);
                BigDecimal rightBigDecimal = NumberUtils.convertNumberToTargetClass(rightNumber, BigDecimal.class);
                BigDecimal sub = leftBigDecimal.subtract(rightBigDecimal);
                if (sub.compareTo(BigDecimal.ZERO) > 0) {
                    return 1;
                } else if (sub.compareTo(BigDecimal.ZERO) < 0) {
                    return -1;
                }
            } else if (leftNumber instanceof Double && rightNumber instanceof Double) {
                Double sub = leftNumber.doubleValue() - rightNumber.doubleValue();
                if (sub > 0.0) {
                    return 1;
                } else if (sub < 0.0) {
                    return -1;
                }
            } else if (leftNumber instanceof Integer && rightNumber instanceof Integer) {
                Integer sub = leftNumber.intValue() - rightNumber.intValue();
                if (sub > 0) {
                    return 1;
                } else if (sub < 0) {
                    return -1;
                }
            }
        } else if (leftValue instanceof String && rightValue instanceof String) {
            return leftValue.toString().compareTo(rightValue.toString());
        } else if (leftValue instanceof Boolean && rightValue instanceof Boolean) {
            throw new UnsupportedOperationException();
        }
        //TODO  可补充完整的类型判断，目前只完成少数数字 字符 Boolean
        return 0;
    }

}