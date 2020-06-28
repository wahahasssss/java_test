package com.hdu.chapter1;

import io.snappydata.jdbc.ClientDriver;
import org.apache.spark.api.java.JavaRDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/11/13
 * @Time 上午10:42
 */
public class SnappydataWithJDBC {


    public static void main(String[] args) throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:snappydata://127.0.0.1:1527");
        System.out.println(c.getTypeMap().toString());
    }
}
