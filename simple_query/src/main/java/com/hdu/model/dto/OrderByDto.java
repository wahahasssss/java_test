package com.hdu.model.dto;

import com.hdu.model.enums.OrderByTypeEnum;
import lombok.*;

/**
 * @ClassName：OrderDto
 * @Description：OrderDto
 * @Author：ssf
 * @Date：2021/9/7 12:46 下午
 * @Versiion：1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderByDto {
    private String orderByField;
    private OrderByTypeEnum orderType;
}