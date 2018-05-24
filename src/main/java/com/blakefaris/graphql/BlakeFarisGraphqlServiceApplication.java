package com.blakefaris.graphql;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
@EnableAutoConfiguration
public class BlakeFarisGraphqlServiceApplication {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

	public static void main(String[] args) {
		SpringApplication.run(BlakeFarisGraphqlServiceApplication.class, args);
	}
}
