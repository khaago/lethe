package com.khaago.lethe.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.springdata20.repository.config.EnableIgniteRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableIgniteRepositories
public class SpringDataConfig {

    @Bean
    public Ignite igniteInstance() {
        return Ignition.start("ignite-spring-data.xml");
    }
}