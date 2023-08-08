package com.flotte.flotte.app.api.user.ui.security;


import com.flotte.flotte.app.api.user.ui.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;


@Configuration
@EnableWebSecurity

public class WebSecurity extends WebSecurityConfigurerAdapter {
    private Environment environment;
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public WebSecurity(Environment environment, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.environment = environment;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


//      //http.csrf().disable().cors().and();
//        //http.authorizeRequests().antMatchers("/**").hasIpAddress(environment.getProperty("gateway.ip"))
//       // http.cors();
//      http.csrf().disable().cors().and().addFilterBefore(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//              http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.headers().frameOptions().disable();
//        http.authorizeRequests().antMatchers(HttpMethod.GET,"/user/user/*").permitAll();
//        http.authorizeRequests().antMatchers(HttpMethod.POST,"/login").permitAll()
//                .and()
//                .addFilter(getAuthticateFilter());
////
//
//
//        http.authorizeRequests().anyRequest().authenticated();
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable().cors().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                .antMatchers("/users/status/check").permitAll()
                .antMatchers(HttpMethod.GET,"/users/users/id/**").permitAll()
                .antMatchers("/users/users/all").authenticated()
              //  .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JwtAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class)
                .headers().frameOptions().disable()
                .and()
                .addFilter(getAuthticateFilter());


    }


    private AuthenticationFilter getAuthticateFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, environment, authenticationManager());
        // authenticationFilter.setAuthenticationManager(authenticationManager());
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }


}
