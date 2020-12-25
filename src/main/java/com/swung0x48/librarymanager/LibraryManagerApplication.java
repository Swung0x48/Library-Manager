package com.swung0x48.librarymanager;

import com.swung0x48.librarymanager.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LibraryManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagerApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);

		registrationBean.addUrlPatterns("/api/books/*");
		return registrationBean;
	}
}
