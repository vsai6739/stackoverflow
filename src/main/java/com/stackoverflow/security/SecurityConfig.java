package com.stackoverflow.security;

import com.stackoverflow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // Enables @PreAuthorize annotations
public class SecurityConfig {

    UserService userService;

    @Autowired
    public SecurityConfig(@Lazy UserService userService) {
        this.userService = userService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/users/register", "/users/login").permitAll()
                        .requestMatchers("/users/**").hasAnyRole("USER", "ADMIN") // Specific to users
                        .requestMatchers("/admin/**").hasRole("ADMIN") // Specific to admin
                        .anyRequest().authenticated() // All remaining paths require authentication
                )

                .formLogin(form -> form
                        .loginPage("/users/login")
                        .usernameParameter("email") // Map "email" field to Spring Security's username parameter
                        .passwordParameter("password") // This is optional; "password" is the default
                        .successHandler(new CustomAuthenticationSuccessHandler(userService))
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Custom logout endpoint
                        .logoutSuccessUrl("/users/login?logout") // Redirect after logout
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt for password hashing
    }
}
