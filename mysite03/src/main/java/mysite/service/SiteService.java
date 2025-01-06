package mysite.service;

import mysite.repository.SiteRepository;
import mysite.vo.SiteVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class SiteService implements InitializingBean {
    private final SiteRepository siteRepository;
    private SiteVo siteVo;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public SiteVo getSite() {
        return siteRepository.findOneSite();
    }

    public void updateSite(SiteVo siteVo,String filename) {
        siteVo.setId(siteVo.getId());
        siteVo.setProfile(filename);
        if (siteRepository.updateSite(siteVo)>=1){
            this.siteVo=siteVo;
        };
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.siteVo = siteRepository.findOneSite();
    }

    public SiteVo getSiteVo() {
        return siteVo;
    }
}
