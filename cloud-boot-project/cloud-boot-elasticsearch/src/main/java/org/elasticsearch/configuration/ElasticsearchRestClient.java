package org.elasticsearch.configuration;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchRestClient {

    final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
    /**
     * ES地址,ip:port
     */
    @Value("${elasticsearch.ip}")
    String ipPort;
    /**
     * ES地址,ip:port
     */
    @Value("${elasticsearch.username}")
    String userName;
    /**
     * ES地址,ip:port
     */
    @Value("${elasticsearch.passwd}")
    String passwd;

    @Bean
    public RestClientBuilder restClientBuilder() {
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, passwd));
        RestClientBuilder.HttpClientConfigCallback httpClientConfigCallback =  new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                return  httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }
        };
        return RestClient.builder(makeHttpHost(ipPort)).setHttpClientConfigCallback(httpClientConfigCallback);
    }


    @Bean(name = "highLevelClient")
    public RestHighLevelClient highLevelClient(@Autowired RestClientBuilder restClientBuilder) {
        restClientBuilder.setMaxRetryTimeoutMillis(60000);
        return new RestHighLevelClient(restClientBuilder.build());
    }


    private HttpHost makeHttpHost(String s) {
        String[] address = s.split(":");
        String ip = address[0];
        int port = Integer.parseInt(address[1]);
        return new HttpHost(ip, port, "http");
    }
}
