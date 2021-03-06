package org.diveintojee.poc.digitaloceanboxprovider;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@SuppressWarnings("unchecked")
public class DigitalOceanBoxProviderConfiguration {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
