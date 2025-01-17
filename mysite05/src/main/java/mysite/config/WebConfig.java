package mysite.config;

import mysite.config.web.FileUploadConfig;
import mysite.config.web.LocaleConfig;
import mysite.config.web.MvcConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({MvcConfig.class, LocaleConfig.class, FileUploadConfig.class})
@ComponentScan(basePackages = {"mysite.controller", "mysite.exception"})
public class WebConfig {
}
