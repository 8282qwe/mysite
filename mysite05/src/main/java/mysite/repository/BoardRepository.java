package mysite.repository;

import mysite.vo.BoardVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardRepository {
    private final SqlSession sqlSession;

    public BoardRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int countBoard(String keyword) {
        return sqlSession.selectOne("board.countBoard", keyword.isBlank() ? "" : "%" + keyword + "%");
    }

    public List<BoardVo> findAllBoard(int currentPage, int pageSize, String keyword) {
        return sqlSession.selectList("board.findAllBoard", Map.of("currentPage",(currentPage-1)*pageSize,
                "pageSize",pageSize,
                "keyword",keyword.isBlank() ? "" : "%" + keyword + "%"));
    }

    public boolean insertBoard(String title, String content, Long writer) {
        BoardVo vo = new BoardVo();
        vo.setTitle(title);
        vo.setContents(content);
        vo.setUser_id(writer);
        vo.setO_no(1);
        return sqlSession.insert("board.insertBoard",vo) > 0;
    }

    public BoardVo findById(Long id) {
        return sqlSession.selectOne("board.findById", id);
    }

    public int deleteBoardById(BoardVo isDelete, Long id) {
        return sqlSession.delete("board.deleteBoardById", Map.of("id",id,
                "board",isDelete));
    }

    public int updateBoard(BoardVo vo) {
        return sqlSession.update("board.updateBoard", vo);
    }

    public boolean updateReply(int g_no, int o_no) {
        return sqlSession.update("board.updateReply", Map.of("g_no", g_no,
                "o_no", o_no)) > 0;
    }

    public void insertReply(int g_no, int o_no, int depth, Long user_id, String title, String contents) {
        updateReply(g_no, o_no);
        BoardVo vo = new BoardVo();
        vo.setTitle(title);
        vo.setContents(contents);
        vo.setUser_id(user_id);
        vo.setO_no(o_no);
        vo.setG_no(g_no);
        vo.setDepth(depth);
        sqlSession.insert("board.insertBoard", vo);
    }

    public void updateViewById(Long id) {
        sqlSession.update("board.updateViewById", id);
    }
}
