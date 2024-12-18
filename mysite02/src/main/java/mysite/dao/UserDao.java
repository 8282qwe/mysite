package mysite.dao;

import mysite.vo.UserVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao extends MyConnection{

    public int insert(UserVo vo) {
        int result = 0;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("insert into user values (null,?,?,?,?,now());");
        ) {
            pstmt.setString(1, vo.getName());
            pstmt.setString(2, vo.getEmail());
            pstmt.setString(3, vo.getPassword());
            pstmt.setString(4, vo.getGender());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return result;
    }
}
