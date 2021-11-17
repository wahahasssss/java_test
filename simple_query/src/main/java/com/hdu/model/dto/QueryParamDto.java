package com.hdu.model.dto;

import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName：QueryParamDto
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/9/6 10:42 下午
 * @Versiion：1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class QueryParamDto {
    private List<Map<String, Object>> data;
    private WhereDto where;
    private OrderByDto orderBy;
    private LimitDto limit;
}