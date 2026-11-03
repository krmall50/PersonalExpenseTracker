package com.krmall50.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import io.github.cdimascio.dotenv.Dotenv;

public class DatabaseConfig {
    private static final Dotenv dotenv = Dotenv.load();

    public static DataSource getDataSource() {
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dotenv.get("DB_URL"));
        config.setUsername(dotenv.get("DB_ USERNAME"));
        config.setPassword(dotenv.get("DB_PASSWORD"));

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        try (HikariDataSource dataSource = new HikariDataSource(config)) {
            return dataSource;
        }
    }

    public static DataSource getDataSourceTest() {
        final HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dotenv.get("DB_URL_TEST"));
        config.setUsername(dotenv.get("DB_ USERNAME"));
        config.setPassword(dotenv.get("DB_PASSWORD"));

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        try (HikariDataSource dataSource = new HikariDataSource(config)) {
            return dataSource;
        }
    }
}
