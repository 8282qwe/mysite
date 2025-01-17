package mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class GuestbookLogRepository {
    private final SqlSession sqlSession;

    public GuestbookLogRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public int insert() {
        return sqlSession.update("guestbook_log.insert");
    }

    public int update() {
        return sqlSession.update("guestbook_log.update");
    }

    public int update(String regDate) {
        return sqlSession.update("guestbook_log.updateByRegDate", regDate);
    }

}
