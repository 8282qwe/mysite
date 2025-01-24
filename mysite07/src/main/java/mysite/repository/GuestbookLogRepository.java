package mysite.repository;

import lombok.AllArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class GuestbookLogRepository {
    private final SqlSession sqlSession;

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
