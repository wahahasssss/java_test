package com.hdu.jdbc;

import com.facebook.presto.jdbc.PrestoPreparedStatement;
import com.facebook.presto.jdbc.PrestoStatement;

import java.sql.*;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/12/27
 * @Time 上午10:57
 */
public class PrestoJdbcTest {

    static final String URL = "jdbc:presto://localhost:8080/hive/test_ssf";

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, "students_text", null);
        String sql = "SELECT * FROM students_text";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Long result = resultSet.getLong(1);

            System.out.println(result);
        }
    }
}


