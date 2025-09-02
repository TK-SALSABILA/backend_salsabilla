package org.school.backend.adapters.infrastructure.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    private final ClerkAuthFilter clerkAuthFilter;

    public SecurityConfig(ClerkAuthFilter clerkAuthFilter) {
        this.clerkAuthFilter = clerkAuthFilter;
    }

    @Bean
    public FilterRegistrationBean<ClerkAuthFilter> loggingFilter(){
        FilterRegistrationBean<ClerkAuthFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(clerkAuthFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}