package com.swung0x48.librarymanager;

import com.swung0x48.librarymanager.filter.AdminFilter;
import com.swung0x48.librarymanager.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class LibraryManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagerApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        registrationBean.setFilter(new CorsFilter(source));
        registrationBean.setOrder(0);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> filterRegistrationBean() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        AuthFilter authFilter = new AuthFilter();
        registrationBean.setFilter(authFilter);

        registrationBean.addUrlPatterns("/api/book/delete/*");
        registrationBean.addUrlPatterns("/api/book/add");
        registrationBean.addUrlPatterns("/api/book/home");
        registrationBean.addUrlPatterns("/api/order/**");
        registrationBean.addUrlPatterns("/api/order/lend");
        registrationBean.addUrlPatterns("/api/order/my");
        registrationBean.addUrlPatterns("/api/user/role");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> filterAdminBean() {
        FilterRegistrationBean<AdminFilter> adminBean = new FilterRegistrationBean<>();
        AdminFilter adminFilter = new AdminFilter();
        adminBean.setFilter(adminFilter);

        adminBean.addUrlPatterns("/api/book/admin/*");
        return adminBean;
    }
}
