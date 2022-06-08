package com.altafjava.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.altafjava.constant.AppConstant;

@Configuration
@ComponentScan("com.altafjava.repository")
@PropertySource(value = { "classpath:application.properties" })
public class DatabaseConfig {

	@Autowired
	private Environment environment;
	@Autowired
	private DataSource dataSource;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty(AppConstant.DB_DRIVER_CLASS_NAME));
		dataSource.setUrl(environment.getProperty(AppConstant.DB_URL));
		dataSource.setUsername(environment.getProperty(AppConstant.DB_USERNAME));
		dataSource.setPassword(environment.getProperty(AppConstant.DB_PASSWORD));
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}

}
