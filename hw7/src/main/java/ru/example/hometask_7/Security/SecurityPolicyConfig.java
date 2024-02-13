package ru.example.hometask_7.Security;

import org.hibernate.internal.log.SubSystemLogging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = false, jsr250Enabled = true)
public class SecurityPolicyConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                            authorizationManagerRequestMatcherRegistry
                                    .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.POST, "*web/delete**").hasRole("ADMIN")
                                    .requestMatchers(HttpMethod.POST, "*web/create**").hasRole("ADMIN")
                                    .requestMatchers("*/web/admin**").hasAnyRole("ADMIN")
                                    .requestMatchers("*/web/home**").hasAnyRole("USER", "ADMIN")
                                    .requestMatchers("*/web/login**").permitAll()
                                    .anyRequest().authenticated()
                    )

                .httpBasic(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        return http.build();
    }
}