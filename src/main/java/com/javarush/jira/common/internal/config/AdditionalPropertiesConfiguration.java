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
///////////////////////////////@PropertySource("classpath:___application-secret___.properties__") prod dev profiles
public class AdditionalPropertiesConfiguration {
//    @Value("${DATABASE.USERNAME}")
//    private String DATABASE;
//    @Value("${DATABASE.USERNAME2}")
//    private String DATABASE2;
    @Autowired
    private Environment env;
    @Bean("configurationFile")public //String
        PropertySourcesPlaceholderConfigurer
    propertiesOpen() {
            //System.out.println("DATABASE.USERNAME= "+DATABASE+DATABASE2); ///////////////////////////////
            //System.out.println("property database = "+env.getProperty("DATABASE.USERNAME"));

            //final PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        PropertySourcesPlaceholderConfigurer propertiesSPC= new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new Resource[ ] {
                //new FileSystemResource("/myth/app/data/weblogic_configuration/config/conf.properties"),
                // prod dev profiles
                ///////////////////////new FileSystemResource("___application-secret___.properties__")
        };
        //Resource resource=new FileSystemResource("src/main/resources/conf.properties")
        propertiesSPC.setLocations(resources);
        propertiesSPC.setIgnoreResourceNotFound(true);
        propertiesSPC.setIgnoreUnresolvablePlaceholders(false);
        return propertiesSPC;
        //return "";
    }
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer(); //необходимо для ${database.password}
    }
    /*@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("guru.springframework.controllers"))
                .paths(or(regex("/register.*"),regex("/api/v1.*"))).build();

    }

     */
}
