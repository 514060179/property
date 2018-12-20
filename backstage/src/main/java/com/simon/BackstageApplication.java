package com.simon;

import com.simon.backstage.filter.RestFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.simon.backstage.dao")
public class BackstageApplication {

	@Bean
	public FilterRegistrationBean restFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean(new RestFilter());
		registration.addUrlPatterns("/*"); //
		registration.setName("restFilter");
		registration.setOrder(1);//排序 升序
		return registration;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackstageApplication.class, args);
	}
}
