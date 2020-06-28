package com.hdu;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午2:22
 */

/**
 * 触发器
 */
public interface Trigger {

    /**
     * 事件码
     * @return
     */
    String getCode();

    /**
     * 是否自动触发
     * @return
     */
    boolean isAutoTrigger();
}
