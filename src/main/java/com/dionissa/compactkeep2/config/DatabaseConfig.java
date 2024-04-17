package com.dionissa.compactkeep2.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DatabaseConfig {
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://sql10.freesqldatabase.com:3306/sql10699795");
        dataSource.setUsername("sql10699795");
        dataSource.setPassword("xvSZCxLKt2");
        return dataSource;
    }
}
