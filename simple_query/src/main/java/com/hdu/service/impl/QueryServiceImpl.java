package com.hdu.service.impl;

import com.hdu.model.dto.QueryParamDto;
import com.hdu.model.enums.OrderByTypeEnum;
import com.hdu.operations.ObjectComparator;
import com.hdu.service.ConditionJudgeService;
import com.hdu.service.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName：QueryServiceImpl
 * @Description：查询服务实现类
 * @Author：ssf
 * @Date：2021/9/6 10:35 下午
 * @Versiion：1.0
 */
@Service
@Slf4j
public class QueryServiceImpl implements QueryService {
    @Autowired
    private ConditionJudgeService conditionJudgeService;

    private static final ObjectComparator comparator = new ObjectComparator();

    @Override
    public List<Map<String, Object>> query(QueryParamDto param) {
        List<Map<String, Object>> queryResults = param.getData()
                .stream()
                .filter(t -> conditionJudgeService.judge(t, param.getWhere()))
                .sorted((map1, map2) -> {
                    String orderByField = param.getOrderBy().getOrderByField();
                    if (param.getOrderBy().getOrderType() == OrderByTypeEnum.ASC) {
                        return comparator.comparator(map1.get(orderByField), map2.get(orderByField));
                    } else if (param.getOrderBy().getOrderType() == OrderByTypeEnum.DESC) {
                        return comparator.comparator(map2.get(orderByField), map1.get(orderByField));
                    }
                    return 0;
                }).limit(param.getLimit().getLimit())
                .collect(Collectors.toList());
        return queryResults;
    }


}