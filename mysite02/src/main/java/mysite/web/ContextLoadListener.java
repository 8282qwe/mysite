package mysite.web;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        String contextConfigLocation = sc.getInitParameter("contextConfigLocation");

        System.out.println("Application[MySite02] starts..." + contextConfigLocation);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // nothing
    }
}
