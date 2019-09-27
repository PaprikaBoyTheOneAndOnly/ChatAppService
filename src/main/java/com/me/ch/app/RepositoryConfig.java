package com.me.ch.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.me.ch")
public class RepositoryConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("create.sql")
                .build();
    }

    @Bean
    public JdbcTemplate createJdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource());

        return template;
    }
}
