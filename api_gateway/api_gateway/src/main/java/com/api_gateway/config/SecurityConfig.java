package com.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //    private static final String[] SWAGGER_WHITELIST = {
//            "/api-docs/**",
//            "/swagger-ui/**",
//            "/swagger-ui.html",
//            "/aggregate/**",
//            "/v3/api-docs/**",
//            "/swagger-resources/**"
//    };
    private static final String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/api-docs/**",
            "/aggregate/**",
            "/aggregate/order-service/v3/api-docs",
            "/webjars/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(SWAGGER_WHITELIST).permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults())) // ✅ use JWT validation
                .build();
    }
}
