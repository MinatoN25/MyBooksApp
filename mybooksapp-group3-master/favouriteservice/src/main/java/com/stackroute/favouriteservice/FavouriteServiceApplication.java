package com.stackroute.favouriteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.favouriteservice.auth.JwtFilter;

@SpringBootApplication
public class FavouriteServiceApplication {

	private static final String APP_PATH="/api/books/*";

	public static void main(String[] args) {
		SpringApplication.run(FavouriteServiceApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		FilterRegistrationBean<JwtFilter> filterRegistrationBean=new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns(APP_PATH);
		return filterRegistrationBean;
	}
}

