package ray1024.blps.configuration;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ray1024.blps.model.entity.Role;
import ray1024.blps.security.jwt.filter.JwtTokenFilter;
import ray1024.blps.service.UserService;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> response.setStatus(HttpStatus.FORBIDDEN.value());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, UserService userService, JwtTokenFilter jwtTokenFilter) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(c -> c
                        .requestMatchers("/ping").permitAll()
                        .requestMatchers(POST, "/api/videos:new").hasRole(Role.RoleEnum.ROLE_CLIENT.name())
                        .requestMatchers(PUT, "/api/videos/*").hasRole(Role.RoleEnum.ROLE_CLIENT.name())
                        .requestMatchers(GET, "/api/videos/*/status").hasRole(Role.RoleEnum.ROLE_CLIENT.name())
                        .requestMatchers(POST, "/api/videos/*:publish").hasRole(Role.RoleEnum.ROLE_CLIENT.name())
                        .requestMatchers(GET, "/api/videos").permitAll()
                        .requestMatchers(GET, "/api/videos/*").permitAll()
                        .requestMatchers(POST, "/api/comments").hasRole(Role.RoleEnum.ROLE_CLIENT.name())
                        .requestMatchers(GET, "/api/comments").permitAll()
                        .requestMatchers(POST, "/api/reactions").hasRole(Role.RoleEnum.ROLE_CLIENT.name())
                        .requestMatchers(GET, "/api/reactions").permitAll()
                        .requestMatchers(GET, "/api/reactions/my").hasRole(Role.RoleEnum.ROLE_CLIENT.name())
                        .requestMatchers(PUT, "/api/sources/*").hasRole(Role.RoleEnum.ROLE_CLIENT.name())
                        .requestMatchers(GET, "/api/sources/*").permitAll()
                        .requestMatchers(POST, "/api/auth/login").permitAll()
                        .anyRequest().denyAll()
                )
                .userDetailsService(userService)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(c -> c
                        .authenticationEntryPoint(authenticationEntryPoint())
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .build();
    }
}