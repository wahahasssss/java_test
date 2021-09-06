package com.hdu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName：GodEntity
 * @Description：TODO
 * @Author：ssf
 * @Date：2020/12/12 11:05 上午
 * @Versiion：1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GodEntity {
    private String id;
    private String name;
    private Integer age;
    private String label;
}