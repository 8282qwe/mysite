package mysite.event;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mysite.service.SiteService;
import mysite.vo.SiteVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class ApplicationContextEventListener {
    private ApplicationContext applicationContext;
    private SiteService siteService;

    @EventListener({ContextRefreshedEvent.class})
    public void handlerContextRefreshEvent() {
        log.info("**********Context Refreshed Event Received*************");

        SiteVo site = siteService.getSite();
        registerOrUpdateSiteVoBean(site);
    }

    private void registerOrUpdateSiteVoBean(SiteVo site) {
        ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();

        if (beanFactory.containsBean("sitevo")) {
            // 이미 존재하는 경우 업데이트
            SiteVo existingSite = beanFactory.getBean("sitevo", SiteVo.class);
            BeanUtils.copyProperties(site, existingSite);
        } else {
            // 새로 등록
            beanFactory.registerSingleton("sitevo", site);
        }
    }
}
