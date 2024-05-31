package com.example.demo.admin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private AdminDetailsServiceImple adminDetailsService;
    private BuyerDetailsServiceImple userDetailsService;

    public SecurityConfig(AdminDetailsServiceImple adminDetailsService, BuyerDetailsServiceImple userDetailsService) {
        this.adminDetailsService = adminDetailsService;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    @Order(2)
    public SecurityFilterChain adminFilterChain(HttpSecurity http,
                                                CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                                                CustomAccessDeniedHandler customAccessDeniedHandler) throws Exception {

        http
                .securityMatcher(new AntPathRequestMatcher("/admin/**"))
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/admin/.vscode/**", "/admin/css/**", "/admin/icons/**", "/admin/images/**"
                                        , "/admin/js/**", "/admin/logincss/**", "/admin/vendor/**"
                                        , "/admin/login", "/admin/logout", "/admin/tetetest"
                                        , "/error", "/error/**", "/errorpage/**").permitAll()
                                .requestMatchers("admin/main").hasRole(Role.ADMIN.name())
                                .requestMatchers("admin/buyermanagement").hasRole(Role.BUYER.name())
                                .anyRequest().authenticated())
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/admin/login")
                                .loginProcessingUrl("/admin/loginProc")
                                .usernameParameter("adminId")
                                .defaultSuccessUrl("/admin/main", true)
                                )
                .logout((logoutConfig) ->
                        logoutConfig
                                .logoutUrl("/admin/logout")
                                .logoutSuccessUrl("/admin/login"))
                .userDetailsService(adminDetailsService)
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                                .accessDeniedHandler(customAccessDeniedHandler))
        ;
        return http.build();
    }

    @Bean
    @Order(0)
    public SecurityFilterChain buyerFilterChain(HttpSecurity http,
                                                CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                                                CustomAccessDeniedHandler customAccessDeniedHandler) throws Exception {
        http
                .securityMatcher(new AntPathRequestMatcher("/buyer/**"))
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(
                                "/css/**", "/assets/**"
                                        , "/demo/**", "/images/**", "/js/**", "/plugins/**"
                                        ,"/buyer/**", "/buyer/detail", "/seller/**"
                                        , "/error", "/error/**", "/errorpage/**"
                                ).permitAll()
                                .requestMatchers("/buyer/detail").hasRole(Role.BUYER.name())
                                .anyRequest().authenticated())
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/buyer/reallogin")
                                .loginProcessingUrl("/buyer/loginProc")
                                .failureUrl("/seller/login?error")
                                .usernameParameter("buyerId")
                                .passwordParameter("buyerPwd")
                                .defaultSuccessUrl("/buyer/main", true))
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login"))
                .userDetailsService(userDetailsService)
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                                .accessDeniedHandler(customAccessDeniedHandler));
        return http.build();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain sellerFilterChain(HttpSecurity http,
                                                 CustomAuthenticationEntryPoint customAuthenticationEntryPoint,
                                                 CustomAccessDeniedHandler customAccessDeniedHandler) throws Exception {
        http
                .securityMatcher(new AntPathRequestMatcher("/seller/**"))
                .csrf(AbstractHttpConfigurer::disable)
                .headers(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(
                                        "/css/**", "/assets/**"
                                        , "/demo/**", "/images/**", "/js/**", "/plugins/**"
                                        , "/seller/**"
                                        , "/error", "/error/**", "/errorpage/**"
                                ).permitAll()
                                // .requestMatchers("/buyer/detail").hasRole(Role.BUYER.name())
                                .anyRequest().authenticated())
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/seller/reallogin")
                                .loginProcessingUrl("/seller/loginProc")
                                .failureUrl("/seller/login?error1")
                                .usernameParameter("sellerId")
                                .passwordParameter("sellerPwd")
                                .defaultSuccessUrl("/seller/index", true))
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login"))
                .userDetailsService(userDetailsService)
                .exceptionHandling((exceptionHandling) ->
                        exceptionHandling
                                .authenticationEntryPoint(customAuthenticationEntryPoint)
                                .accessDeniedHandler(customAccessDeniedHandler));
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
