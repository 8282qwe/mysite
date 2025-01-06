package mysite.service;

import mysite.repository.SiteRepository;
import mysite.vo.SiteVo;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SiteService {
    private final SiteRepository siteRepository;
    private final ApplicationContext applicationContext;

    public SiteService(SiteRepository siteRepository, ApplicationContext applicationContext) {
        this.siteRepository = siteRepository;
        this.applicationContext = applicationContext;
    }

    public SiteVo getSite() {
        return siteRepository.findOneSite();
    }

    public void updateSite(SiteVo siteVo,String filename) {
        SiteVo site = applicationContext.getBean(SiteVo.class);
        siteVo.setId(site.getId());
        siteVo.setProfile(filename);
        if (siteRepository.updateSite(siteVo)>=1){
            applicationContext.getBean(SiteVo.class).setTitle(siteVo.getTitle());
        };
    }
}
