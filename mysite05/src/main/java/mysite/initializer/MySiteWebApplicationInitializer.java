package mysite.initializer;

import jakarta.servlet.Filter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import mysite.config.AppConfig;
import mysite.config.WebConfig;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.util.ResourceBundle;

public class MySiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] {AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] {WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new DelegatingFilterProxy("springSecurityFilterChain")};
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        ResourceBundle bundle = ResourceBundle.getBundle("mysite.config.web.fileupload");

        long maxFileSize = Long.parseLong(bundle.getString("fileUpload.maxFileSize"));
        long maxRequestSize = Long.parseLong(bundle.getString("fileUpload.maxRequestSize"));
        int fileSizeThreshold = Integer.parseInt(bundle.getString("fileUpload.fileSizeThreshold"));

        MultipartConfigElement configElement = new MultipartConfigElement(null,maxFileSize,maxRequestSize,fileSizeThreshold);
        registration.setMultipartConfig(configElement);
    }

    
}
