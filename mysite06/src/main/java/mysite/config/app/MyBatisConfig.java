package mysite.config.app;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@Import(DBConfig.class)
public class MyBatisConfig {

    private static final String MYBATIS_CONFIG_LOCATION = "classpath:mysite/config/app/mybatis/configuration.xml";

    @Bean
    public SqlSessionFactory sqlSessionFactory(ApplicationContext applicationContext, DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource(MYBATIS_CONFIG_LOCATION));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public SqlSession sqlSession(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
