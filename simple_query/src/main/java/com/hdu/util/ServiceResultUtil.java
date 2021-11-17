package com.hdu.util;

import com.hdu.model.dto.ResultDto;
import com.hdu.model.enums.MessageCodeEnum;

import java.util.List;

/**
 * @ClassName：ServiceResultUtil
 * @Description：返回接通工具类
 * @Author：ssf
 * @Date：2021/9/6 10:26 下午
 * @Versiion：1.0
 */
public class ServiceResultUtil {
    public static ResultDto success() {
        return success(null);
    }

    public static ResultDto success(List<Object> body) {
        return ResultDto.builder().body(body)
                .code(MessageCodeEnum.SUCCESS.getCode())
                .build();
    }

}