package mysite.dao;

import mysite.vo.UserVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends MyConnection {

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

    public UserVo findByEmailAndPassword(String email, String password) {
        UserVo vo = null;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select id,name from user where email=? and password=?;");
        ) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                vo = new UserVo();
                vo.setId(rs.getLong("id"));
                vo.setName(rs.getString("name"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return vo;
    }

    public UserVo findById(long id) {
        UserVo vo = null;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select name,email,password,gender from user where id = ?;");
        ) {
            pstmt.setLong(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                vo = new UserVo();
                vo.setId(id);
                vo.setName(rs.getString("name"));
                vo.setEmail(rs.getString("email"));
                vo.setPassword(rs.getString("password"));
                vo.setGender(rs.getString("gender"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return vo;
    }

    public int updateById(UserVo vo) {
        int result = 0;

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(vo.getPassword().isEmpty() ? "update user set name = ?,email = ?, gender = ? where id=?;" : "update user set name = ?,email = ?, gender = ?,password = ? where id=?;");
        ) {
            int i = 1;
            pstmt.setString(i++, vo.getName());
            pstmt.setString(i++, vo.getEmail());
            pstmt.setString(i++, vo.getGender());
            if (!vo.getPassword().isEmpty()) {
                pstmt.setString(i++, vo.getPassword());
            }
            pstmt.setLong(i++, vo.getId());

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return result;
    }
}
