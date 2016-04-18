package com.jpmorgan.awm.pb.mortgageorigination.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.jpmorgan.awm.pb.mortgageorigination")
public class MortgageConfiguration {

}
