package postgresExample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostgreExample {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {

            Class.forName("org.postgresql.Driver");
            Connection connection = null;
            connection = DriverManager.getConnection("jdbc:postgresql://192.168.56.188:5432/mydb", "postgres", "123456");
            String sqlString = "SELECT * FROM students";
            Statement statement = connection.createStatement();
            int value = 123;
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE id = ?");
            preparedStatement.setInt(1, value);
            ResultSet resultSet = statement.executeQuery(sqlString);
            ResultSet pResultSet = preparedStatement.executeQuery();
            while (pResultSet.next()) {
                System.out.println(pResultSet.getString(2));
            }
            resultSet.close();
            pResultSet.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
        }
    }


}
