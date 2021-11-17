package com.hdu.operations;

import org.springframework.util.NumberUtils;

import java.math.BigDecimal;

/**
 * @ClassName：OpGE
 * @Description：大于等于
 * @Author：ssf
 * @Date：2021/9/6 11:23 下午
 * @Versiion：1.0
 */
public class OpGE implements SimpleOperator {
    @Override
    public Boolean operate(Object leftValue, Object rightValue) {
        if (leftValue instanceof Number && rightValue instanceof Number) {
            Number leftNumber = (Number) leftValue;
            Number rightNumber = (Number) rightValue;
            if (leftNumber instanceof BigDecimal && rightNumber instanceof BigDecimal) {
                BigDecimal leftBigDecimal = NumberUtils.convertNumberToTargetClass(leftNumber, BigDecimal.class);
                BigDecimal rightBigDecimal = NumberUtils.convertNumberToTargetClass(rightNumber, BigDecimal.class);
                return leftBigDecimal.compareTo(rightBigDecimal) >= 0;
            } else if (leftNumber instanceof Double && rightNumber instanceof Double) {
                return leftNumber.doubleValue() >= rightNumber.doubleValue();
            } else if (leftNumber instanceof Integer && rightNumber instanceof Integer) {
                return leftNumber.intValue() >= rightNumber.intValue();
            }
        } else if (leftValue instanceof String && rightValue instanceof String) {
            return leftValue.toString().compareTo(rightValue.toString()) >= 0;
        } else if (leftValue instanceof Boolean && rightValue instanceof Boolean) {
            throw new UnsupportedOperationException();
        }
        //TODO  可补充完整的类型判断，目前只完成少数数字 字符 Boolean
        return false;
    }
}