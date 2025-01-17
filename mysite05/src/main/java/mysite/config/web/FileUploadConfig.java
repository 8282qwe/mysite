package mysite.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@PropertySource("classpath:mysite/config/web/fileupload.properties")
public class FileUploadConfig implements WebMvcConfigurer {

    private final Environment env;

    public FileUploadConfig(Environment env) {
        this.env = env;
    }

    //    Multipart Resolver
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    //    MVC URL-Resource Mapping
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(env.getProperty("fileUpload.resourceUrl") + "/**")
                .addResourceLocations("file:" + env.getProperty("fileUpload.uploadLocation") + "/");
    }
}
