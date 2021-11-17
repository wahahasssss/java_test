package com.hdu;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/4/10
 * @Time 下午2:19
 */
public abstract class ContextBase<T extends State> {
    private T contextData;

    public T getContextData() {
        return contextData;
    }
}
