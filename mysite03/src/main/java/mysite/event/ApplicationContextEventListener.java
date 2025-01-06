package mysite.event;

import mysite.service.SiteService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public class ApplicationContextEventListener {
    private final ApplicationContext applicationContext;

    public ApplicationContextEventListener(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void handlerContextRefreshEvent() {
        SiteService siteService = applicationContext.getBean(SiteService.class);
        System.out.println("**********Context Refreshed Event Received*************");
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
        context.getBeanFactory().registerSingleton("sitevo", siteService.getSite());
    }
}
