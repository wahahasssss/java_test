package com.hdu.model.dto;

import com.hdu.model.enums.LogicEnum;
import lombok.*;

import java.util.List;

/**
 * @ClassName：WhereDto
 * @Description：TODO
 * @Author：ssf
 * @Date：2021/9/6 10:43 下午
 * @Versiion：1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class WhereDto {
    private LogicEnum logicEnum;
    List<WhereDetailDto> wheres;
}