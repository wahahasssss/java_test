package com.hdu.springboot_k8s.model;

import lombok.*;

import java.util.Map;

/**
 * @ClassName：ResultDto
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/8/3 10:34 下午
 * @Versiion：1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ResultDto {
    private String msg;
    private Integer code;
    private Map<String, String> data;
}