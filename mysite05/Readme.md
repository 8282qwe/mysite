[src/main/java]

    com (X)
    |--- poscodx (X)
    |	|--- mysite
    |	|	|--- config
    |	|	|	|--- AppConfig.java (ApplicationContext.xml)
    |	|	|	|--- WebConfig.java (Spring-Servlet.xml)
    |	|	|	|--- app
    |	|	|	|	|--- DBConfig.java
    |	|	|	|	|--- MyBatisConfig.java
    |	|	|	|--- web
    |	|	|	|	|--- MvcConfig.java
    |	|	|	|	|--- SecurityConfig.java
    |	|	|	|	|--- LocaleConfig.java
    |	|	|	|	|--- FileuploadConfig.java
    |   |   |--- initializer
    |   |   |       |--- MySiteWebApplicationInitializer
    |	|	|--- controller
    |	|	|--- service
    |	|	|--- repository

[src/main/resources]

    com (X)
    |--- poscodx (X)
    |	|--- mysite
    |	|	|--- config
    |       |	|	|--- app
    |       |	|	|	|--- jdbc.properties
    |       |	|	|	|--- mybatis
    |       |	|	|		|--- configuration.xml
    |       |	|	|		|--- mappers
    |       |	|	|		|	|--- board.xml
    |       |	|	|		|	|--- user.xml
    |       |	|	|--- web
    |       |	|	|	|--- fileupload.properties
    |       |	|	|	|--- messages
    |       |	|	|	|	|--- message_ko.properties
    |       |	|	|	|	|--- message_en.properties