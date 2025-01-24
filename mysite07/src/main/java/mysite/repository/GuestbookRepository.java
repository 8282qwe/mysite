package mysite.repository;

import lombok.AllArgsConstructor;
import mysite.vo.GuestbookVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Repository
public class GuestbookRepository {
    private SqlSession sqlSession;

    public List<GuestbookVo> findAll() {
        return sqlSession.selectList("guestbook.findAll");
    }

    public int insert(GuestbookVo vo) {
        return sqlSession.insert("guestbook.insert", vo);
    }

    public int deleteByIdAndPassword(long id, String password) {
        return sqlSession.delete("guestbook.deleteByIdAndPassword", Map.of("id", id, "password", password));
    }

    public GuestbookVo findById(String id) {
        return sqlSession.selectOne("guestbook.findById",Long.parseLong(id));
    }
}
