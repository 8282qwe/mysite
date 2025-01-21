package mysite;

import mysite.config.AppConfig;
import mysite.config.WebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AppConfig.class, WebConfig.class})
public class MySiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySiteApplication.class, args);
    }

}
