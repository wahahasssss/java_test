package com.hdu.server.service;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/8/27
 * @Time 下午2:04
 */
public class MutServiceImpl implements MutService.Iface{
    private static final Logger LOGGER = LoggerFactory.getLogger(MutServiceImpl.class);

    @Override
    public String execute(String info) throws TException {
        LOGGER.info("server received message {}",info);
        return "received";
    }
}
