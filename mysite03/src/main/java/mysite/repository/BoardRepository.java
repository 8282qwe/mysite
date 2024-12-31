package mysite.repository;

import mysite.vo.BoardVo;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BoardRepository extends MyConnection {

    public int countBoard(String keyword) {
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select count(*) from board where title like ?");
                ) {

            pstmt.setString(1, keyword.isBlank() ? "%" : "%"+keyword+"%");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return 0;
    }

    public List<BoardVo> findAllBoard(int currentPage, int pageSize,String keyword) {
        List<BoardVo> result = new ArrayList<>();

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select b.id as id, title,contents,hit, date_format(b.reg_date,'%Y-%m-%d %h:%i:%s') as reg_date_format, g_no,o_no,depth,user_id,name from board b join user u where u.id = b.user_id and b.title like ? order by g_no desc, o_no asc limit ?,?;");
        ) {
            pstmt.setString(1, keyword.isBlank() ? "%" : "%"+keyword+"%");
            pstmt.setInt(2, (currentPage - 1) * pageSize);
            pstmt.setInt(3, pageSize);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                BoardVo board = new BoardVo();
                board.setId(rs.getLong("id"));
                board.setTitle(rs.getString("title"));
                board.setContents(rs.getString("contents"));
                board.setHit(rs.getInt("hit"));
                board.setReg_date(rs.getString("reg_date_format"));
                board.setG_no(rs.getInt("g_no"));
                board.setO_no(rs.getInt("o_no"));
                board.setDepth(rs.getInt("depth"));
                board.setUser_id(rs.getLong("user_id"));
                board.setUser_name(rs.getString("name"));
                result.add(board);
            }
            return result;

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return result;
    }

    public boolean insertBoard(String title, String content, Long writer) {
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("insert into board values (null,?,?,0,now(),(select max_g_no+1 from (select IFNULL(MAX(g_no),0) max_g_no from board) temp),1,0,?);");
        ) {
            pstmt.setString(1, title);
            pstmt.setString(2, content);
            pstmt.setLong(3, writer);

            return pstmt.executeUpdate() >= 1;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }

    public BoardVo findById(Long id) {
        BoardVo board = null;
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("select b.id as id, title,contents,hit, date_format(reg_date,'%Y-%m-%d %h:%i:%s') as reg_date_format, g_no,o_no,depth,user_id from board b join user u where b.user_id = u.id and b.id=?;");
        ) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                board = new BoardVo();
                board.setId(rs.getLong("id"));
                board.setTitle(rs.getString("title"));
                board.setContents(rs.getString("contents"));
                board.setHit(rs.getInt("hit"));
                board.setReg_date(rs.getString("reg_date_format"));
                board.setG_no(rs.getInt("g_no"));
                board.setO_no(rs.getInt("o_no"));
                board.setDepth(rs.getInt("depth"));
                board.setUser_id(rs.getLong("user_id"));
                board.setUser_name(rs.getString("name"));
            }

        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return board;
    }

    public void deleteBoardById(BoardVo isDelete,Long id) {
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM board WHERE g_no = ? AND o_no >= ? AND (o_no < (SELECT MIN(o_no) FROM board WHERE g_no = ? AND o_no > ? AND depth <= ?) OR (SELECT MIN(o_no) FROM board WHERE g_no = ? AND o_no > ? AND depth <= ?) IS NULL) and user_id = ?;");
        ) {
            pstmt.setInt(1, isDelete.getG_no());
            pstmt.setInt(2, isDelete.getO_no());
            pstmt.setInt(3, isDelete.getG_no());
            pstmt.setInt(4, isDelete.getO_no());
            pstmt.setInt(5, isDelete.getDepth());
            pstmt.setInt(6, isDelete.getG_no());
            pstmt.setInt(7, isDelete.getO_no());
            pstmt.setInt(8, isDelete.getDepth());
            pstmt.setLong(9, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public void updateBoard(BoardVo vo) {
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("update board set title = ?,contents=? where id = ?;");
        ) {
            pstmt.setString(1, vo.getTitle());
            pstmt.setString(2, vo.getContents());
            pstmt.setLong(3, vo.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public boolean updateReply(int g_no, int o_no) {
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("update board set o_no = o_no+1 where g_no = ? and o_no >= ?;")
        ) {
            pstmt.setInt(1, g_no);
            pstmt.setInt(2, o_no + 1);

            return pstmt.executeUpdate() >= 1;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
        return false;
    }

    public void insertReply(int g_no, int o_no, int depth, Long user_id, String title, String contents) {
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("insert into board values (null,?,?,0,now(),?,?,?,?);");
        ) {
            updateReply(g_no, o_no);

            pstmt.setString(1, title);
            pstmt.setString(2, contents);
            pstmt.setInt(3, g_no);
            pstmt.setInt(4, o_no + 1);
            pstmt.setInt(5, depth + 1);
            pstmt.setLong(6, user_id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }

    public void updateViewById(Long id) {
        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement("update board set hit = hit+1 where id = ?;")
        ) {
            pstmt.setLong(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
