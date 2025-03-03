package mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MyConnection {
    public Connection getConnection() throws SQLException {
        Connection conn = null;

        try {
            // 1. JDBC Driver 로딩
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. 연결하기
            String url = "jdbc:mariadb://192.168.0.120:3306/webdb";
            conn = DriverManager.getConnection(url, "webdb", "webdb");

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패: " + e.getMessage());
        }
        return conn;
    }
}
