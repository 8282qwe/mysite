package mysite.repository;

import mysite.vo.SiteVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class SiteRepository {
    private final SqlSession sqlSession;

    public SiteRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public SiteVo findOneSite() {
        return sqlSession.selectOne("site.findOneSite");
    }

    public int updateSite(SiteVo siteVo) {
        return sqlSession.update("site.updateSite", siteVo);
    }
}
