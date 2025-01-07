package mysite.event;

import mysite.service.SiteService;
import mysite.vo.SiteVo;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
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

        // 내가 했던 풀이(ApplicationContext에서 beanfactory를 가져와서 등록)
//        ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
//        context.getBeanFactory().registerSingleton("sitevo", siteService.getSite());

        SiteVo site = siteService.getSite();
        GenericBeanDefinition beanDefinition = getGenericBeanDefinition(site);

        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
        registry.registerBeanDefinition("sitevo",beanDefinition);
    }

    private static GenericBeanDefinition getGenericBeanDefinition(SiteVo site) {
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues.add("id", site.getId());
        propertyValues.add("title", site.getTitle());
        propertyValues.add("welcome", site.getWelcome());
        propertyValues.add("description", site.getDescription());
        propertyValues.add("profile", site.getProfile());

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(SiteVo.class);
        beanDefinition.setPropertyValues(propertyValues);
        return beanDefinition;
    }
}
