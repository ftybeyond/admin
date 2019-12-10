package com.ftysoft.project.face.megvii.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class HttpBeans {

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager(HttpClientPoolConfig httpClientPoolConfig) {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(httpClientPoolConfig.getMaxTotal());
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(httpClientPoolConfig.getDefaultMaxPerRoute());
        return poolingHttpClientConnectionManager;
    }

    @Bean
    public RequestConfig RequestConfigBuilder(HttpClientPoolConfig httpClientPoolConfig) {
        return RequestConfig.custom()
                .setConnectTimeout(httpClientPoolConfig.getConnectTimeout())
                .setSocketTimeout(httpClientPoolConfig.getSocketTimeout())
                .setConnectionRequestTimeout(httpClientPoolConfig.getConnectionRequestTimeout())
                .setStaleConnectionCheckEnabled(httpClientPoolConfig.isStaleConnectionCheckEnabled())
                .build();
    }

    @Bean
    public HttpClientBuilder httpClientBuilder(PoolingHttpClientConnectionManager poolingHttpClientConnectionManager){
        return HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager);
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CloseableHttpClient closeableHttpClient(HttpClientBuilder httpClientBuilder){
        return httpClientBuilder.build();
    }



}
