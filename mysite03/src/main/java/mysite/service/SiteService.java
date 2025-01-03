package mysite.service;

import mysite.repository.SiteRepository;
import mysite.vo.SiteVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class SiteService implements InitializingBean {
    private final SiteRepository siteRepository;
    private Long id;
    private String title;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public SiteVo getSite() {
        return siteRepository.findOneSite();
    }

    public void updateSite(SiteVo siteVo,String filename) {
        siteVo.setId(id);
        siteVo.setProfile(filename);
        if (siteRepository.updateSite(siteVo)>=1){
            this.title = siteVo.getTitle();
        };
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        SiteVo siteVo = siteRepository.findOneSite();
        this.title = siteVo.getTitle();
        this.id = siteVo.getId();
    }
}
