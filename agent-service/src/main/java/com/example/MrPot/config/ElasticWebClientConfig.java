package com.example.MrPot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ElasticWebClientConfig {

    @Bean
    public WebClient elasticWebClient(
            @Value("${app.elastic.base-url}") String baseUrl,
            @Value("${app.elastic.username:}") String username,
            @Value("${app.elastic.password:}") String password,
            WebClient.Builder builder
    ) {
        WebClient.Builder configured = builder.baseUrl(baseUrl);
        if (StringUtils.hasText(username)) {
            configured.defaultHeaders(h -> h.setBasicAuth(username, password));
        }
        return configured.build();
    }
}
