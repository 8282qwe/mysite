package mysite.repository;

import lombok.AllArgsConstructor;
import mysite.vo.SiteVo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@AllArgsConstructor
@Repository
public class SiteRepository {
    private final SqlSession sqlSession;

    public SiteVo findOneSite() {
        return sqlSession.selectOne("site.findOneSite");
    }

    public int updateSite(SiteVo siteVo) {
        return sqlSession.update("site.updateSite", siteVo);
    }
}
