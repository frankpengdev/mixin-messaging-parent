package com.feikongbao.message.wechat.config;

import com.feikongbao.messaging.core.config.MessagingCoreConfig;
import com.yodoo.megalodon.datasource.annotation.EnableCompanyDataSource;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.sql.DataSource;

/**
 * 配置类
 *
 * @author Wang Zi Li
 * @date
 */
@ComponentScan(basePackages = {"com.feikongbao.message.wechat"})
@MapperScan(value = {"com.feikongbao.message.wechat.model.mapper"},
        sqlSessionFactoryRef = MessageWeChatConfiguration.WECHAT_SQL_SESSION_FACTORY_BEAN_NAME,
        sqlSessionTemplateRef = MessageWeChatConfiguration.WECHAT_SQL_SESSION_TEMPLATE_BEAN_NAME)
@EnableTransactionManagement
@Import({MessageWeChatRestTemplateConfiguration.class,
        MessagingCoreConfig.class,
        MessageWeChatMvcWebAppConfigurer.class})
@Configuration
@EnableCompanyDataSource
public class MessageWeChatConfiguration {

    private Logger logger = LoggerFactory.getLogger(MessageWeChatConfiguration.class);

    public static final String WECHAT_SQL_SESSION_FACTORY_BEAN_NAME = "messageWeChatSqlSessionFactory";
    public static final String WECHAT_SQL_SESSION_TEMPLATE_BEAN_NAME = "messageWeChatSqlSessionTemplate";
    private static final String WECHAT_DATA_SOURCE_TRANSACTION_MANAGER_BEAN_NAME = "messageWeChatTransactionManager";


    @Bean(WECHAT_DATA_SOURCE_TRANSACTION_MANAGER_BEAN_NAME)
    public DataSourceTransactionManager messageWeChatTransactionManager(DataSource companyDataSource) {
        return new DataSourceTransactionManager(companyDataSource);
    }


    @Bean(WECHAT_SQL_SESSION_FACTORY_BEAN_NAME)
    @SuppressWarnings("Duplicates")
    public SqlSessionFactory messageWeChatSqlSessionFactory(DataSource companyDataSource) {
        TransactionFactory transactionFactory = new SpringManagedTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, companyDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }

    @Bean(WECHAT_SQL_SESSION_TEMPLATE_BEAN_NAME)
    public SqlSessionTemplate messageWeChatSqlSessionTemplate(SqlSessionFactory messageWeChatSqlSessionFactory) {
        return new SqlSessionTemplate(messageWeChatSqlSessionFactory, ExecutorType.SIMPLE);
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
