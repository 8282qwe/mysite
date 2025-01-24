package mysite.service;

import lombok.AllArgsConstructor;
import mysite.repository.SiteRepository;
import mysite.vo.SiteVo;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SiteService {
    private final SiteRepository siteRepository;
    private final ApplicationContext applicationContext;

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
