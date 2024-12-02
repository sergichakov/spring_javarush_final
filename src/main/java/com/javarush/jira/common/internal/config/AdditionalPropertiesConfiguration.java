package com.javarush.jira.common.internal.config;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
@NoArgsConstructor
//@AllArgsConstructor

public class AdditionalPropertiesConfiguration {

    @Autowired
    private Environment env;
    @Bean("configurationFile")
    public PropertySourcesPlaceholderConfigurer propertiesOpen() {
        PropertySourcesPlaceholderConfigurer propertiesSPC= new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new Resource[ ] {
        };
        propertiesSPC.setLocations(resources);
        propertiesSPC.setIgnoreResourceNotFound(true);
        propertiesSPC.setIgnoreUnresolvablePlaceholders(false);
        return propertiesSPC;
    }
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer(); //необходимо для ${database.password}
    }
}
