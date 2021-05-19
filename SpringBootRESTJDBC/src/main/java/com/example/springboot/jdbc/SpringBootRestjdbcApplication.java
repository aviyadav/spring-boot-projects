package com.example.springboot.jdbc;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
@Configuration
@ComponentScan("com.example.springboot.jdbc")
@PropertySource("classpath:database.properties")
public class SpringBootRestjdbcApplication {

    @Autowired
    Environment environment;
    
    private final String URL = "url";
    private final String USER = "dbuser";
    private final String DRIVER = "driver";
    private final String PASSWORD = "dbpassword";
    
    @Bean
    DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(environment.getProperty(URL));
        ds.setUsername(environment.getProperty(USER));
        ds.setPassword(environment.getProperty(PASSWORD));
        ds.setDriverClassName(environment.getProperty(DRIVER));
        
        return ds;
    }
    
    @Bean
    JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRestjdbcApplication.class, args);
    }

}
