package com.javarush.jira;

import com.javarush.jira.common.internal.config.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableCaching
//@PropertySource(value="classpath:___application-secret___.properties__")   //___application-secret___.properties__
public class JiraRushApplication {
    public static void main(String[] args) {
        SpringApplication.run(JiraRushApplication.class, args);

    }

}
