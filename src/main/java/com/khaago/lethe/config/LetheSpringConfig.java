package com.khaago.lethe.config;

import com.khaago.lethe.util.AbstractTwoWayConverter;
import com.khaago.lethe.util.ClientDtoConverter;
import com.khaago.lethe.util.EventDtoConverter;
import com.khaago.lethe.util.TopicDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Configuration
public class LetheSpringConfig {

    private final ClientDtoConverter clientDtoConverter;
    private final EventDtoConverter eventDtoConverter;
    private final TopicDtoConverter topicDtoConverter;

    @Autowired
    public LetheSpringConfig(ClientDtoConverter clientDtoConverter, EventDtoConverter eventDtoConverter, TopicDtoConverter topicDtoConverter) {
        this.clientDtoConverter = clientDtoConverter;
        this.eventDtoConverter = eventDtoConverter;
        this.topicDtoConverter = topicDtoConverter;
    }

    @Bean(name="conversionService")
    public ConversionService getConversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        bean.setConverters(new HashSet<>(Arrays.asList(clientDtoConverter, eventDtoConverter, topicDtoConverter))); //add converters
        bean.afterPropertiesSet();
        return bean.getObject();
    }
}
