package mysite.repository;

import mysite.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserRepository {

    private final SqlSession sqlSession;

    public UserRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int insert(UserVo vo) {
        return sqlSession.insert("user.insert", vo);
    }

    public UserVo findByEmailAndPassword(String email, String password) {
        return sqlSession.selectOne("user.findByEmailAndPassword", Map.of("email", email, "password", password));
    }

    public UserVo findByEmail(String email) {
        return sqlSession.selectOne("user.findByEmail", email);
    }

    public UserVo findById(long id) {
        return sqlSession.selectOne("user.findById", id);
    }

    public boolean updateById(UserVo vo) {
        return sqlSession.update("user.updateById", vo) > 0;
    }
}
