package mysite.repository;

import mysite.vo.UserVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

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
        StopWatch sw = new StopWatch();
        sw.start();
        sw.stop();
        System.out.println(sw.prettyPrint());
        System.out.println("[Execution Time][UserRepository.findByEmailAndPassword]: ]" + sw.getTotalTimeMillis() + "mills");
        return sqlSession.selectOne("user.findByEmailAndPassword", Map.of("email", email, "password", password));
    }

    public UserVo findById(long id) {
        return sqlSession.selectOne("user.findById", id);
    }

    public boolean updateById(UserVo vo) {
        return sqlSession.update("user.updateById", vo) > 0;
    }
}
