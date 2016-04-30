package com.jpmorgan.awm.pb.mortgageorigination.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.jpmorgan.awm.pb.mortgageorigination")
@PropertySource("classpath:application.properties")
@ImportResource("classpath:drools-context.xml")
public class MortgageConfiguration {

	@Value("${oracle.db.url}")
	private String oracledbUrl;

	@Value("${oracle.db.user}")
	private String oracleUser;

	@Value("${oracle.db.password}")
	private String oraclePassword;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public DataSource dataSourceOracle() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl(oracledbUrl);
		dataSource.setUsername(oracleUser);
		dataSource.setPassword(oraclePassword);
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSourceOracle) {
		return new JdbcTemplate(dataSourceOracle);
	}

}
