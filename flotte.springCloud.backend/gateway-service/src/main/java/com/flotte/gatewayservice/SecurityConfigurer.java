//package com.flotte.gatewayservice;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import reactor.core.publisher.Mono;
//
//@EnableWebFluxSecurity
//@EnableReactiveMethodSecurity
//public class SecurityConfigurer {
//
//
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        return http
//                .exceptionHandling()
//                .authenticationEntryPoint((swe, e) -> {
//                    return Mono.fromRunnable(() -> {
//                        swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                    });
//                }).accessDeniedHandler((swe, e) -> {
//                    return Mono.fromRunnable(() -> {
//                        swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
//                    });
//                }).and()
//                .csrf().disable()
//                .formLogin().disable()
//                .httpBasic().disable()
//                        .authorizeExchange()
//                .pathMatchers(HttpMethod.OPTIONS).permitAll()
//                .pathMatchers("/login").permitAll()
//                .anyExchange().authenticated()
//                .and().build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
