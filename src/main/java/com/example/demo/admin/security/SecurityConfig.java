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

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private AdminUserDetailsServiceImple adminUserDetailsService;
    private UserUserDetailsServiceImple userUserDetailsService;

    public SecurityConfig(AdminUserDetailsServiceImple adminUserDetailsService, UserUserDetailsServiceImple userUserDetailsService) {
        this.adminUserDetailsService = adminUserDetailsService;
        this.userUserDetailsService = userUserDetailsService;
    }

    @Bean
    public SecurityFilterChain adminFilterChain(HttpSecurity http,
                                                CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                                                CustomAccessDeniedHandler customAccessDeniedHandler) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/admin/.vscode/**", "/admin/css/**", "/admin/icons/**", "/admin/images/**"
                                        , "/admin/js/**", "/admin/logincss/**", "/admin/vendor/**", "/assets/**", "/css/**"
                                        , "/demo/**", "/images/**", "/js/**", "/plugins/**"
                                        , "/admin/login", "/admin/logout", "/admin/tetetest", "/seller/**"
                                        , "/error", "/error/**", "/custom_error/**", "/errorpage/**").permitAll()
                                .requestMatchers("admin/main").hasRole(Role.ADMIN.name())
                                .requestMatchers("admin/buyermanagement").hasRole(Role.BUYER.name())
                                .anyRequest().authenticated())
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/admin/login")
                                .loginProcessingUrl("/admin/loginProc")
                                .usernameParameter("adminId")
                                .defaultSuccessUrl("/admin/main", true))
                .logout((logoutConfig) ->
                        logoutConfig
                                .logoutUrl("/admin/logout")
                                .logoutSuccessUrl("/admin/login"))
                .userDetailsService(adminUserDetailsService)
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                                .accessDeniedHandler(customAccessDeniedHandler))
        ;
        return http.build();
    }

    @Bean
    public SecurityFilterChain buyerFilterChain(HttpSecurity http,
                                                CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                                                CustomAccessDeniedHandler customAccessDeniedHandler) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/buyer/css/**", "/buyer/js/**", "/buyer/images/**"
                                , "/buyer/**", "/buyer/detail").permitAll()
                                .requestMatchers("/buyer/detail").hasRole(Role.BUYER.name())
                                .anyRequest().authenticated())
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/seller/login")
                                .loginProcessingUrl("/buyer/loginProc")
                                .usernameParameter("buyerId")
                                .passwordParameter("buyerPwd")
                                .defaultSuccessUrl("/buyer/main", true))
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login"))
                .userDetailsService(userUserDetailsService);
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
