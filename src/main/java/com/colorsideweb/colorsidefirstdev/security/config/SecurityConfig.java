package com.colorsideweb.colorsidefirstdev.security.config;

//import com.colorsideweb.colorsidefirstdev.security.filters.ApiKeyFilter;
import com.colorsideweb.colorsidefirstdev.security.filters.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Value("${private.api.key}")
    private String API_KEY;


    @Bean
    public SecurityFilterChain securityFilterChain(@NonNull HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
//                .authorizeHttpRequests().requestMatchers("/api/v1/auth/**").permitAll()
                .authorizeHttpRequests().anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                //.addFilterBefore(new ApiKeyFilter(API_KEY), JwtAuthenticationFilter.class)
                .build();
    }
}