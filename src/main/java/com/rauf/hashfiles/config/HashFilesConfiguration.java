package com.rauf.hashfiles.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
public class HashFilesConfiguration {

    @Value("${hashfiles.datasourse.url}")
    String jdbcUrl;
    @Value("${hashfiles.datasourse.username}")
    String username;
    @Value("${hashfiles.datasourse.password}")
    String password;

    @Bean("dataSource")
    DataSource dataSource() {
        HikariConfig hc = new HikariConfig();
        hc.setJdbcUrl(jdbcUrl);
        hc.setUsername(username);
        hc.setPassword(password);
        hc.setDriverClassName("com.mysql.jdbc.Driver");
        hc.setMaximumPoolSize(30);
        return new HikariDataSource(hc);
    }
}
