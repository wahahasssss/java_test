package com.hdu.operations;

import com.hdu.model.enums.ExpressionEnum;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @ClassName：OperationPools
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/9/6 11:53 下午
 * @Versiion：1.0
 */
public class OperationPools {
    private static final HashMap<ExpressionEnum, SimpleOperator> operators = new HashMap<>();

    static {
        operators.put(ExpressionEnum.EQ, new OpEQ());
        operators.put(ExpressionEnum.LT, new OpLT());
        operators.put(ExpressionEnum.GE, new OpGE());
        operators.put(ExpressionEnum.GT, new OpGT());
        operators.put(ExpressionEnum.LE, new OpLE());
    }

    public static SimpleOperator getOperator(ExpressionEnum expression) {
        return operators.get(expression);
    }
}