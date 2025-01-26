package com.ouaskanas.educonnect.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, JwtGenerator jwtGenerator) throws Exception {
        http.csrf((csrf)-> csrf.disable()).authorizeHttpRequests(request->
                request.requestMatchers("/api/v1/friendship/**",
                                "/api/v1/classroom/**",
                                "/api/v1/users/students",
                                "/api/v1/users/teachers").authenticated()
                        .requestMatchers("/api/v1/friendship/**",
                                "/api/v1/classroom/createClassroom/**",
                                "/api/v1/classroom/allclassrooms",
                                "/api/v1/users/currentuser/",
                                "/api/v1/users/suggestions/").authenticated()
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter(jwtGenerator), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtGenerator jwtGenerator){
        return new JwtAuthenticationFilter();
    }

}
