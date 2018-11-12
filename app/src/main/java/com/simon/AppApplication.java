package com.simon;

import com.simon.app.filter.RestFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@MapperScan("com.simon.app.dao")
public class AppApplication {

	@Bean
	public FilterRegistrationBean restFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new RestFilter());
		registration.addUrlPatterns("/test/*"); //
//		registration.addInitParameter("paramName", "paramValue"); //
		registration.setName("testFilter");
		registration.setOrder(1);
		return registration;
	}
//	@Bean
//	public FilterRegistrationBean jwtFilter() {
//		FilterRegistrationBean registration = new FilterRegistrationBean(new JwtFilter());
//		registration.addUrlPatterns("/test/*"); //
//		registration.setName("jwtFilter");
//		registration.setOrder(2);
//		return registration;
//	}


	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}
}
