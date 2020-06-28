import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class JDBCExample {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:sqlserver://192.168.1.66\\SQLSERVER2005;databaseNmae=TgUserHardIdDB;user=TgUserHardId;password=sfd2f3hf";
            String mysqlUrl = "jdbc:mysql://localhost:3306/firstmysqldb";

            System.out.println("begin");
            Connection conn = DriverManager.getConnection(url);
            Connection mysqlConn = DriverManager.getConnection(mysqlUrl, "root", "3344520lp");
            System.out.println("end");
            String sql = "SELECT TOP 10 * FROM dbo.HardIds";
            String mySqlString = "SELECT  * FROM wahaha LIMIT 3";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getInt(2));
            }
            Statement mysqlstatement = mysqlConn.createStatement();
            ResultSet mySqlResultSet = mysqlstatement.executeQuery(mySqlString);
            while (mySqlResultSet.next()) {
                System.out.println(mySqlResultSet.getString(2));
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("找不到驱动类，驱动加载错误");
            e.printStackTrace();
        }
    }

}
