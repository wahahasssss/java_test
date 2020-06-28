package com.hdu.service;

import org.springframework.stereotype.Service;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2019/5/7
 * @Time 2:44 PM
 */
public interface IService {
    void queryAllData();
    Long transactionTest();
    void readAfterCommit();
}
