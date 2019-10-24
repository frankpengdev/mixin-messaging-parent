package com.mixin.messaging.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mixin.config.company.annotation.EnableCompanyMySqlDataSourceConfig;
import com.mixin.config.company.annotation.EnableCompanyRabbitMqConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.mixin.config.company.CompanyMySqlDataSourceConfig.COMPANY_MYSQL_MYBATIS_SESSION_FACTORY_BEAN_NAME;


/**
 * @Author jinjun_luo
 * @Date 2019/4/11 11:10
 **/
@Configuration
@ComponentScan(basePackages= {"com.mixin.messaging.core"})
@MapperScan(basePackages = "com.mixin.messaging.core.mapper", sqlSessionFactoryRef = COMPANY_MYSQL_MYBATIS_SESSION_FACTORY_BEAN_NAME)
@EnableTransactionManagement
@EnableCompanyRabbitMqConfig
@EnableCompanyMySqlDataSourceConfig
public class MessagingCoreConfig {

    private final Logger logger = LoggerFactory.getLogger(MessagingCoreConfig.class);



    /** DataSourceTransactionManager*/
//    public final static String MESSAGE_CORE_TRANSACTION_MANAGER = "messageCoreTransactionManager";

    /** SqlSessionFactory*/
//    public final static String MESSAGE_CORE_SQL_SESSION_FACTORY = "messageCoreSqlSessionFactory";

    /**
     * 系列化
     * @return
     */
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

//    @Bean(MESSAGE_CORE_TRANSACTION_MANAGER)
//    public DataSourceTransactionManager transactionManager(DataSource companyDataSource) {
//        return new DataSourceTransactionManager(companyDataSource);
//    }
//
//    @Bean(MESSAGE_CORE_SQL_SESSION_FACTORY)
//    public SqlSessionFactory sqlSessionFactory(DataSource companyDataSource) {
//        TransactionFactory transactionFactory = new SpringManagedTransactionFactory();
//        Environment environment = new Environment("development", transactionFactory, companyDataSource);
//        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
//        return new SqlSessionFactoryBuilder().build(configuration);
//    }
}