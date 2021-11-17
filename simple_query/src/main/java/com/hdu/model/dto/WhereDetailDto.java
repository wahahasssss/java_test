package com.hdu.model.dto;

import lombok.*;

/**
 * @ClassName：WhereDetailDto
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/9/6 10:49 下午
 * @Versiion：1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class WhereDetailDto {
    private String field;
    private String expression;
    private Object constant;

}