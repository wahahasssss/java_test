package com.hdu.jdbc;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/27
 * @Time 上午11:35
 */
public class ClassLoadTest {

    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {

        Enumeration enumeration = DriverManager.getDrivers();
        while (enumeration.hasMoreElements()) {
            Driver driver = (Driver) enumeration.nextElement();
            System.out.println(driver);
        }

    }
}
