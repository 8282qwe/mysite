package mysite.config;

import mysite.config.app.DBConfig;
import mysite.config.app.MyBatisConfig;
import mysite.event.ApplicationContextEventListener;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement(proxyTargetClass = true)
@Import({DBConfig.class, MyBatisConfig.class})
@ComponentScan(basePackages = {"mysite.service", "mysite.repository", "mysite.aspect"})
public class AppConfig {

    @Bean
    public ApplicationContextEventListener applicationContextEventListener() {
        return new ApplicationContextEventListener();
    }
}
