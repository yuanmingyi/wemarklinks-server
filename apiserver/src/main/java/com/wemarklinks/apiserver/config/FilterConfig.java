package com.wemarklinks.apiserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.wemarklinks.apiserver.filter.CorrelationFilter;

@Configuration
public class FilterConfig {
	@Bean
	@Autowired
	public FilterRegistrationBean addCorrelationFilter(CorrelationFilter correlationFilter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(correlationFilter);
		return registration;
	}
}
