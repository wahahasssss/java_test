package com.hdu.service;

import com.hdu.model.dto.WhereDto;

import java.util.Map;

public interface ConditionJudgeService {
    Boolean judge(Map<String, Object> data, WhereDto where);
}
