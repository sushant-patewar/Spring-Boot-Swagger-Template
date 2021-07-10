package com.webservices.swagger;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {


    @Configuration
    @Order(30)
    public static class HttpSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            
            http.csrf().disable().authorizeRequests()
                                        .antMatchers("/").permitAll()
                                        .antMatchers("/csrf").permitAll()
                                        .antMatchers("/swagger-ui.html", "/swagger-resources/**", "/webjars/**","/v2/api-docs").permitAll()
                                        // .requestMatchers(EndpointRequest.to("info","health")).permitAll() //actuators
                                        .anyRequest().authenticated()
                                        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                        .and().httpBasic();
        }
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(SecurityProperties properties, ObjectProvider<PasswordEncoder> passwordEncoder) {
        return new UserDetailsServiceAutoConfiguration().inMemoryUserDetailsManager(properties, passwordEncoder);
    }
    
}
