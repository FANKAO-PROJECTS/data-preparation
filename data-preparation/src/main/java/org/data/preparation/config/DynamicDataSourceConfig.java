package org.data.preparation.config;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class DynamicDataSourceConfig {
	public DataSource createDataSource(String url, String username, String password, String driverClassName) {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setDriverClassName(driverClassName);
		return new HikariDataSource(hikariConfig);
	}

	public JdbcTemplate createJdbcTemplate(String url, String username, String password, String driverClassName) {
		return new JdbcTemplate(createDataSource(url, username, password, driverClassName));
	}
}
