package com.feikongbao.messaging.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
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
import org.springframework.beans.factory.annotation.Value;
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
@PropertySource({"com/feikongbao/messaging/core/messaging-core.properties"})
@EnableTransactionManagement
public class MessagingCoreConfig {

    private final Logger logger = LoggerFactory.getLogger(MessagingCoreConfig.class);

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;

    @Value("${spring.rabbitmq.publisher-returns}")
    private boolean publisherReturns;

    @Value("${spring.rabbitmq.template.mandatory}")
    private boolean mandatory;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUserName;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    /** DataSource*/
    public final static String MESSAGE_CORE_DATA_SOURCE = "messagingCoreDataSource";

    /** DataSourceTransactionManager*/
    public final static String MESSAGE_CORE_TRANSACTION_MANAGER = "messageCoreTransactionManager";

    /** SqlSessionFactory*/
    public final static String MESSAGE_CORE_SQL_SESSION_FACTORY = "messageCoreSqlSessionFactory";


    /**
     * 连接工厂
     * @return
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        // 地址和端口
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        // 用户名
        connectionFactory.setUsername(username);
        // 密码
        connectionFactory.setPassword(password);
        // 访问路径
        connectionFactory.setVirtualHost(virtualHost);
        // 消息发送到交换机确认机制，是否确认回调
        connectionFactory.setPublisherConfirms(publisherConfirms);
        // 消息从交换器发送到队列确认机制，是否确认回调
        connectionFactory.setPublisherReturns(publisherReturns);
        // connectionFactory.setChannelCacheSize();
        connectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CONNECTION);
        return connectionFactory;
    }

    /**
     * 通过连接工厂rabbitTemplate
     * @return
     */
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        // 发送消息时设置强制标志,设置为true时return callback才生效
        template.setMandatory(mandatory);
        // 对象转json
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        // TODO
        System.out.println("创建RabbitTemplate成功----------------- " + template.toString());
        return template;
    }

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
     * dataSource
     * @return
     */
    @Bean(MESSAGE_CORE_DATA_SOURCE)
    public DataSource messagingCoreDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUserName);
        config.setPassword(dbPassword);
        config.setAutoCommit(true);
        // 最小空闲连接数
        config.setMinimumIdle(5);
        // 最大连接
        config.setMaximumPoolSize(300);
        config.setPoolName("messagingCorePoolName");
        return new HikariDataSource(config);
    }

    /**
     * transaction
     * @param messagingCoreDataSource
     * @return
     */
    @Bean(MESSAGE_CORE_TRANSACTION_MANAGER)
    public DataSourceTransactionManager transactionManager(DataSource messagingCoreDataSource) {
        return new DataSourceTransactionManager(messagingCoreDataSource);
    }

    /**
     *  sqlSessionFactoryBean
     * @param messagingCoreDataSource
     * @return
     */
    @Bean(MESSAGE_CORE_SQL_SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory(DataSource messagingCoreDataSource) {
        TransactionFactory transactionFactory = new SpringManagedTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, messagingCoreDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration(environment);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        return sqlSessionFactory;
    }
}

