package com.hdu.interfaces;

import com.hdu.model.Apple;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/20
 * @Time 下午12:50
 */
public interface IApplePredicate {
    boolean isValid(Apple a);
}
