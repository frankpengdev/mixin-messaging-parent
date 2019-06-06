package com.feikongbao.message.wechat.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Wang Zi Li
 * @date 2019/4/17 17:57
 */
@Configuration
@EnableApolloConfig("wechat")
public class MessageWeChatRestTemplateConfiguration {

    @Value("${messaging_wechat_rest_read_timeout}")
    private Integer readTimeout;

    @Value("${messaging_wechat_rest_connect_timeout}")
    private Integer connectTimeout;

    @Value("${messaging_wechat_rest_time_to_live_seconds}")
    private Long timeToLive;

    @Value("${http_client_connection_manager_max_total}")
    private Integer clientConnectionManagerMaxTotal;

    @Value("${messaging_wechat_rest_retry_count}")
    private Integer retryCount;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));
        restTemplate.setRequestFactory(wechatClientHttpRequestFactory());
        return restTemplate;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory wechatClientHttpRequestFactory() {
        //保持连接
        PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager(timeToLive, TimeUnit.SECONDS);
        clientConnectionManager.setMaxTotal(clientConnectionManagerMaxTotal);

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Connection", "keep-alive"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN,en-US"));
        headers.add(new BasicHeader("Accept-Charset", "UTF-8"));

        HttpClient httpClient = HttpClients.custom()
                .setConnectionManager(clientConnectionManager)
                .setDefaultHeaders(headers)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(retryCount, true))
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .build();

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        httpRequestFactory.setReadTimeout(readTimeout);
        httpRequestFactory.setConnectTimeout(connectTimeout);
        httpRequestFactory.setBufferRequestBody(false);

        return httpRequestFactory;
    }

}
