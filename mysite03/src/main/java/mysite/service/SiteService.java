package mysite.service;

import mysite.repository.SiteRepository;
import mysite.vo.SiteVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class SiteService {
    private final SiteRepository siteRepository;

    @Autowired
    private ApplicationContext applicationContext;

    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public SiteVo getSite() {
        return siteRepository.findOneSite();
    }

    public void updateSite(SiteVo siteVo,String filename) {
        SiteVo site = applicationContext.getBean(SiteVo.class);
        siteVo.setId(site.getId());
        siteVo.setProfile(filename);
        if (siteRepository.updateSite(siteVo)>=1){
            BeanUtils.copyProperties(siteRepository.findOneSite(),site);
        };
    }
}
