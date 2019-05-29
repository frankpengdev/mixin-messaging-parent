package com.feikongbao.messaging.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yodoo.megalodon.datasource.annotation.EnableCompanyDataSource;
import com.yodoo.megalodon.datasource.annotation.EnableRabbitMqConfig;
import com.yodoo.megalodon.datasource.config.RabbitMqConfig;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * @Description TODO
 * @Author jinjun_luo
 * @Date 2019/4/11 11:10
 **/
@Configuration
@ComponentScan(basePackages= {"com.feikongbao.messaging.core"})
@MapperScan(basePackages = "com.feikongbao.messaging.core.mapper", sqlSessionFactoryRef = MessagingCoreConfig.MESSAGE_CORE_SQL_SESSION_FACTORY)
@EnableTransactionManagement
@EnableRabbitMqConfig
@EnableCompanyDataSource
public class MessagingCoreConfig {

    private final Logger logger = LoggerFactory.getLogger(MessagingCoreConfig.class);
    @Autowired
    private RabbitMqConfig rabbitMqConfig;

    /** DataSourceTransactionManager*/
    public final static String MESSAGE_CORE_TRANSACTION_MANAGER = "messageCoreTransactionManager";

    /** SqlSessionFactory*/
    public final static String MESSAGE_CORE_SQL_SESSION_FACTORY = "messageCoreSqlSessionFactory";


    /**
     * rabbitmq 连接工厂
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        // 地址和端口
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitMqConfig.rabbitmqUrlHost,rabbitMqConfig.rabbitmqUrlServicePort);
        // 用户名
        connectionFactory.setUsername(rabbitMqConfig.rabbitmqGeneralUsername);
        // 密码
        connectionFactory.setPassword(rabbitMqConfig.rabbitmqGeneralPassword);
        // 访问路径
        connectionFactory.setVirtualHost(rabbitMqConfig.rabbitmqFeikongbaoVHost);
        // 消息发送到交换机确认机制，是否确认回调
        connectionFactory.setPublisherConfirms(rabbitMqConfig.rabbitmqPublisherConfirms);
        // 消息从交换器发送到队列确认机制，是否确认回调
        connectionFactory.setPublisherReturns(rabbitMqConfig.rabbitmqPublisherReturns);
        // connectionFactory.setChannelCacheSize();
        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
        return connectionFactory;
    }

    /**
     * rabbitmq 通过连接工厂生成 rabbitTemplate
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        // 发送消息时设置强制标志,设置为true时return callback才生效
        template.setMandatory(rabbitMqConfig.rabbitmqTemplateMandatory);
        // 对象转json
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        logger.info("创建RabbitTemplate成功----------------->: {}", template.toString());
        return template;
    }

    /**
     * 系列化
     * @return
     */
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        // mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        // mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        // mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 处理字节
        // SimpleModule simpleModule = new SimpleModule();
        // simpleModule.addSerializer(byte[].class, getByteSerialize());
        // mapper.registerModule(simpleModule);
        // 处理日期
        // mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
        return new ObjectMapper();
    }

    // private static JsonSerializer<byte[]> getByteSerialize() {
    //     return new JsonSerializer<byte[]>() {
    //         @Override
    //         public void serialize(byte[] value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
    //             gen.writeString("");  // 此处可实现字节转成字符串。如压缩等
    //         }
    //     };
    // }

    /**
     * 数据事务 transaction
     * @param companyDataSource
     * @return
     */
    @Bean(MESSAGE_CORE_TRANSACTION_MANAGER)
    public DataSourceTransactionManager transactionManager(DataSource companyDataSource) {
        return new DataSourceTransactionManager(companyDataSource);
    }

    /**
     *  数据库 sqlSessionFactoryBean
     * @param companyDataSource
     * @return
     */
    @Bean(MESSAGE_CORE_SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory(DataSource companyDataSource) {
        TransactionFactory transactionFactory = new SpringManagedTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, companyDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
        return new SqlSessionFactoryBuilder().build(configuration);
    }
}