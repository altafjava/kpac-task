package com.altafjava.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.altafjava.constant.AppConstant;

@Configuration
@ComponentScan("com.altafjava.repository")
@PropertySource(value = { "classpath:database.properties" })
public class JdbcConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty(AppConstant.MYSQL_DRIVER_CLASS_NAME));
		dataSource.setUrl(environment.getProperty(AppConstant.MYSQL_URL));
		dataSource.setUsername(environment.getProperty(AppConstant.MYSQL_USERNAME));
		dataSource.setPassword(environment.getProperty(AppConstant.MYSQL_PASSWORD));
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate() {
		System.out.println("jdbctemplate=" + dataSource());
		return new JdbcTemplate(dataSource());
	}
}
