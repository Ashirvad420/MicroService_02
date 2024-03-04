package com.microservice.comment.config;

import com.microservice.comment.payload.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig { //Rest Class Template

    @Bean
    public RestTemplate getRestTemplate()
    {

        return new RestTemplate();
    }

    public RestTemplate getRestTemplate(String s, Class<Post> postClass) {

        return new RestTemplate();
    }
}

// it help us to intrect with another project
