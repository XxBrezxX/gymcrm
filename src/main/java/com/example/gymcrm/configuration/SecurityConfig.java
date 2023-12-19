package com.example.gymcrm.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.gymcrm.components.JwtTokenFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private JwtTokenFilter jwtTokenFilter;

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.csrf(csrf -> csrf.disable())
                                .authorizeRequests(
                                                requests -> requests.antMatchers(HttpMethod.POST, "/authenticate")
                                                                .permitAll()
                                                                .antMatchers("/h2-console/**").permitAll()
                                                                .anyRequest().authenticated())
                                .headers(headers -> headers.frameOptions().sameOrigin())
                                .formLogin(login -> login.loginPage("/login").permitAll()
                                                .defaultSuccessUrl("/home"))
                                .addFilterBefore(jwtTokenFilter,
                                                UsernamePasswordAuthenticationFilter.class); 
                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

}
