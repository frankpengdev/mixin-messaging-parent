package com.feikongbao.message.wechat.config;

import com.feikongbao.messaging.core.config.MessagingCoreConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;
import java.util.Base64;

/**
 * 配置类
 *
 * @author Wang Zi Li
 * @date
 */
@ComponentScan(basePackages = {"com.feikongbao.message.wechat"})
@PropertySource("/com/feikongbao/message/wechat/wechat_config.properties")
@MapperScan(value = {"com.feikongbao.message.wechat.model.mapper"},
        sqlSessionFactoryRef = MessageWeChatConfiguration.WECHAT_SQL_SESSION_FACTORY_BEAN_NAME,
        sqlSessionTemplateRef = MessageWeChatConfiguration.WECHAT_SQL_SESSION_TEMPLATE_BEAN_NAME)
@EnableTransactionManagement
@Import({MessageWeChatRestTemplateConfiguration.class,
        MessageWeChatCacheConfiguration.class,
        MessagingCoreConfig.class})
@Configuration
public class MessageWeChatConfiguration {

    private Logger logger = LoggerFactory.getLogger(MessageWeChatConfiguration.class);

    public static final String WECHAT_SQL_SESSION_FACTORY_BEAN_NAME = "messageWeChatSqlSessionFactory";
    public static final String WECHAT_SQL_SESSION_TEMPLATE_BEAN_NAME = "messageWeChatSqlSessionTemplate";
    private static final String WECHAT_DATA_SOURCE_TRANSACTION_MANAGER_BEAN_NAME = "messageWeChatTransactionManager";

    @Value("${message.wechat.mysql.connection.url}")
    private String weChatDbUrl;

    @Value("${message.wechat.mysql.connection.user.name}")
    private String weChatDbUserName;

    @Value("${message.wechat.mysql.connection.user.password}")
    private String weChatDbUserPasswordEncrypted;

    @Value("${message.wechat.mysql.connection.max.pool.size}")
    private Integer weChatDbPoolMaxSize;

    @Value("${message.wechat.mysql.connection.min.pool.size}")
    private Integer weChatDbPoolMinSize;

    @Bean("messageWeChatDataSource")
    public DataSource messageWeChatDataSource() {

        HikariDataSource dataSource = DataSourceBuilder.create().type(HikariDataSource.class).build();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl(weChatDbUrl);
        dataSource.setUsername(weChatDbUserName);

        String password = new String(Base64.getMimeDecoder().decode(weChatDbUserPasswordEncrypted));
        dataSource.setPassword(password);
        //空闲数
        dataSource.setMinimumIdle(1);
        dataSource.setMaximumPoolSize(weChatDbPoolMaxSize);

        dataSource.setPoolName("wechat_mysql_thread");

        return dataSource;

    }


    @Bean(WECHAT_DATA_SOURCE_TRANSACTION_MANAGER_BEAN_NAME)
    public DataSourceTransactionManager messageWeChatTransactionManager() {
        return new DataSourceTransactionManager(messageWeChatDataSource());
    }


    @Bean(WECHAT_SQL_SESSION_FACTORY_BEAN_NAME)
    public SqlSessionFactory messageWeChatSqlSessionFactory() {
        TransactionFactory transactionFactory = new SpringManagedTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, messageWeChatDataSource());
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

    @Bean(WECHAT_SQL_SESSION_TEMPLATE_BEAN_NAME)
    public SqlSessionTemplate messageWeChatSqlSessionTemplate(@Qualifier("messageWeChatSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory, ExecutorType.SIMPLE);
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return encodingFilter;
    }

    @Bean
    public ViewResolver viewResolver() {
        FreeMarkerViewResolver freeMarkerViewResolver = new FreeMarkerViewResolver();
        freeMarkerViewResolver.setCache(true);
        freeMarkerViewResolver.setContentType("text/html; charset=UTF-8");
        freeMarkerViewResolver.setSuffix(".html");
        return freeMarkerViewResolver;
    }

    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("classpath:/WEB-INF/view");
        freeMarkerConfigurer.setDefaultEncoding("UTF-8");
        return freeMarkerConfigurer;
    }
}
