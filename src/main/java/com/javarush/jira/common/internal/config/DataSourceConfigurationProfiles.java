package com.javarush.jira.common.internal.config;

//import jakarta.activation.DataSource;
/*
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.javarush.jira.login.internal.web",
        "com.javarush.jira.common"
})
@EnableTransactionManagement
public class DataSourceConfigurationProfiles {
    @Value("${spring.datasource.url")
    private String url;
    @Value("${spring.datasource.username}")
    private String dateBaseUsername;
    @Value("${spring.datasource.password}")
    private String password;
    @Bean
    @Profile("prod")
    //@ConfigurationProperties(prefix="datasource.postgres")
    public DataSource dataSourcePostgres() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(dateBaseUsername);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    @Profile("test")
    //@ConfigurationProperties(prefix="datasource.h2")
    public DataSource dataSourceH2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:testdb");
        dataSource.setUsername(dateBaseUsername);
        dataSource.setPassword(password);

        return dataSource;
    }
}

*/