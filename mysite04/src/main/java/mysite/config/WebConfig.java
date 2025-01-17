package mysite.config;

import mysite.config.web.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({MvcConfig.class, SecurityConfig.class, LocaleConfig.class, FileUploadConfig.class})
@ComponentScan(basePackages = {"mysite.controller", "mysite.exception"})
public class WebConfig {
}
