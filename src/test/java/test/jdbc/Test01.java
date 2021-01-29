package test.jdbc;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test01 {

    public static void main(String[] args) throws Exception {
        Connection connect = getConnect();
        connect.setAutoCommit(false);
        PreparedStatement stmt = connect.prepareStatement("update test set name = '姚放yaofang' where id = ?");
        stmt.setInt(1, 4);
        int rs = stmt.executeUpdate();
        System.out.println(rs);
//		while(rs.next()){
//			System.out.println(rs.getString(1)+"-"+rs.getString(2));
//		}
        connect.commit();
        connect.close();
    }

    public static Connection getConnect() {
        java.sql.Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=GMT&useSSL=false&allowPublicKeyRetrieval=true", "test", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }


        return connection;

    }
}
