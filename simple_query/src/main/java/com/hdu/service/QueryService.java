package com.hdu.service;

import com.hdu.model.dto.QueryParamDto;

import java.util.List;
import java.util.Map;

public interface QueryService {
    List<Map<String, Object>> query(QueryParamDto param);
}
