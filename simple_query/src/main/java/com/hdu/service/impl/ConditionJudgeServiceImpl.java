package com.hdu.service.impl;

import com.hdu.exception.FieldNotExistException;
import com.hdu.model.dto.WhereDetailDto;
import com.hdu.model.dto.WhereDto;
import com.hdu.model.enums.ExpressionEnum;
import com.hdu.model.enums.LogicEnum;
import com.hdu.operations.OperationPools;
import com.hdu.service.ConditionJudgeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName：ConditionJudgeServiceImpl
 * @Description：条件判断
 * @Author：ssf
 * @Date：2021/9/6 11:10 下午
 * @Versiion：1.0
 */
@Slf4j
@Service
public class ConditionJudgeServiceImpl implements ConditionJudgeService {
    @Override
    public Boolean judge(Map<String, Object> data, WhereDto where) {
        return where.getLogicEnum() == LogicEnum.AND ? judgeAnd(data, where.getWheres()) : judgeOr(data, where.getWheres());
    }

    private boolean judgeAnd(Map<String, Object> data, List<WhereDetailDto> wheres) {
        for (WhereDetailDto w : wheres) {
            String field = w.getField();
            if (!data.containsKey(field)) {
                log.error("judgeAnd failed, 请核对字段");
                throw new FieldNotExistException();
            }
            ExpressionEnum express = ExpressionEnum.valueOfExpress(w.getExpression());
            Object rightValue = w.getConstant();
            Object leftValue = data.get(field);
            if (!OperationPools.getOperator(express).operate(leftValue, rightValue)) {
                return false;
            }
        }
        return true;
    }

    private boolean judgeOr(Map<String, Object> data, List<WhereDetailDto> wheres) {
        for (WhereDetailDto w : wheres) {
            String field = w.getField();
            if (!data.containsKey(field)) {
                log.error("judgeOr failed, 请核对字段");
                throw new FieldNotExistException();
            }
            ExpressionEnum express = ExpressionEnum.valueOfExpress(w.getExpression());
            Object rightValue = w.getConstant();
            Object leftValue = data.get(field);
            if (OperationPools.getOperator(express).operate(leftValue, rightValue)) {
                return true;
            }
        }
        return false;
    }
}