package mysite.bean;

import mysite.service.SiteService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class MySiteBean implements InitializingBean {
    private final SiteService siteService;
    private String title;

    public MySiteBean(SiteService siteService) {
        this.siteService = siteService;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void afterPropertiesSet() {
        this.title = siteService.getSite().getTitle();
    }
}
