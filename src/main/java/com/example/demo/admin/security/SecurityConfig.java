package com.example.demo.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private MyUserDetailsServiceImple myUserDetailsService;

    public SecurityConfig(MyUserDetailsServiceImple myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                                           CustomAccessDeniedHandler customAccessDeniedHandler) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/admin/login", "/admin/logout", "/admin/.vscode/**"
                                        , "/admin/css/**", "/admin/icons/**", "/admin/images/**"
                                        , "/admin/js/**", "/admin/logincss/**", "/admin/vendor/**", "/login", "/seller/**", "/buyer/**"
                                        , "/assets/**", "/css/**", "/demo/**", "/images/**", "/js/**", "/plugins/**","/buyer/detail"
                                        , "/error/**").permitAll()
                                .requestMatchers("admin/main").hasRole(Role.ADMIN.name())
                                .anyRequest().authenticated()
                )
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/admin/login")
                                .usernameParameter("adminId")
                                .loginProcessingUrl("/admin/loginProc")
                                .defaultSuccessUrl("/admin/main", true))
                .logout((logoutConfig) ->
                        logoutConfig
                                .logoutUrl("/admin/logout")
                                .logoutSuccessUrl("/admin/login"))
                .userDetailsService(myUserDetailsService)
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                                .accessDeniedHandler(customAccessDeniedHandler))
        ;
        return http.build();
    }

    public class ErrorResponse {

        private final HttpStatus status;
        private final String message;

        public ErrorResponse(HttpStatus status, String message) {
            this.status = status;
            this.message = message;
        }

        public HttpStatus getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
