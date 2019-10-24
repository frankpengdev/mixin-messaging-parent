package com.mixin.messaging.wechat.config;

import com.mixin.config.company.annotation.EnableCompanyMySqlDataSourceConfig;
import com.mixin.messaging.core.config.MessagingCoreConfig;
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

import static com.mixin.config.company.CompanyMySqlDataSourceConfig.COMPANY_MYSQL_MYBATIS_SESSION_FACTORY_BEAN_NAME;

/**
 * 配置类
 *
 * @author Wang Zi Li
 * @date
 */
@ComponentScan(basePackages = {"com.mixin.messaging.wechat"})
@MapperScan(value = {"com.feikongbao.message.wechat.model.mapper"},
        sqlSessionFactoryRef = COMPANY_MYSQL_MYBATIS_SESSION_FACTORY_BEAN_NAME,
        sqlSessionTemplateRef = MessageWeChatConfiguration.WECHAT_SQL_SESSION_TEMPLATE_BEAN_NAME)
@EnableTransactionManagement
@Import({MessageWeChatRestTemplateConfiguration.class,
        MessagingCoreConfig.class,
        MessageWeChatMvcWebAppConfigurer.class})
@Configuration
@EnableCompanyMySqlDataSourceConfig
public class MessageWeChatConfiguration {

    private Logger logger = LoggerFactory.getLogger(MessageWeChatConfiguration.class);

//    public static final String WECHAT_SQL_SESSION_FACTORY_BEAN_NAME = "messageWeChatSqlSessionFactory";
    public static final String WECHAT_SQL_SESSION_TEMPLATE_BEAN_NAME = "messageWeChatSqlSessionTemplate";
//    private static final String WECHAT_DATA_SOURCE_TRANSACTION_MANAGER_BEAN_NAME = "messageWeChatTransactionManager";

    @Bean(WECHAT_SQL_SESSION_TEMPLATE_BEAN_NAME)
    public SqlSessionTemplate messageWeChatSqlSessionTemplate(@Qualifier(COMPANY_MYSQL_MYBATIS_SESSION_FACTORY_BEAN_NAME) SqlSessionFactory messageWeChatSqlSessionFactory) {
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
