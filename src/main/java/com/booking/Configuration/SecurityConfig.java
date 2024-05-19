package com.booking.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;


@Configuration
public class SecurityConfig {

private JwtSecurityFilter jwtSecurityFilter;

    public SecurityConfig(JwtSecurityFilter jwtSecurityFilter) {
        this.jwtSecurityFilter = jwtSecurityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
     //   http.addFilterBefore(jwtSecurityFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests().anyRequest()
                //.requestMatchers("/api/v1/user/addUser","/api/v1/user/verifylogin")
                .permitAll();//.requestMatchers("api/v1/user/profile").hasAnyRole("ADMIN","ROLE")
       // .anyRequest().authenticated();
        return http.build();
    }
}
