package com.hdu.Annation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/9/25
 * @Time 下午5:11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserName {
    String name();

    String passWord();
}
