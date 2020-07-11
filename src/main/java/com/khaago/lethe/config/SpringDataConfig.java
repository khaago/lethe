package com.khaago.lethe.config;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteAtomicLong;
import org.apache.ignite.Ignition;
import org.apache.ignite.springdata20.repository.config.EnableIgniteRepositories;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableIgniteRepositories
public class SpringDataConfig {

    @Bean(name = "igniteInstance")
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Ignite ignite() {
        return Ignition.start("ignite-spring-data.xml");
    }

    @Bean
    public IgniteAtomicLong clientIdGen(){
        return ignite().atomicLong(
                "clientIdGen", // Atomic long name.
                0,        		// Initial value.
                true     		// Create if it does not exist.
        );
    }

    @Bean
    public IgniteAtomicLong topicIdGen(){
        return ignite().atomicLong(
                "topicIdGen",
                0,
                true
        );
    }

    @Bean
    public IgniteAtomicLong groupIdGen(){
        return ignite().atomicLong(
                "groupIdGen",
                0,
                true
        );
    }

    @Bean
    public IgniteAtomicLong eventIdGen(){
        return ignite().atomicLong(
                "eventIdGen",
                0,
                true
        );
    }

}