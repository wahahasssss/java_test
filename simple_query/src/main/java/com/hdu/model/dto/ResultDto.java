package com.hdu.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ClassName：ResultVo
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/9/6 10:23 下午
 * @Versiion：1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto<T> {
    private Integer code;
    private String message;
    private T body;
}