package com.dev.person;

//import com.sid.secservice.serc.filters.JwtAuthenticationFilter;
//import com.sid.secservice.serc.service.AccountService;
//import com.sid.secservice.serc.service.UserDetailsServiceImpl;


import com.dev.person.filtre.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/person/person/mle//**").permitAll()
              //  .antMatchers(HttpMethod.GET).permitAll()
                .anyRequest().authenticated()
               //.anyRequest().permitAll()
                .and()
              .formLogin().and()
              .addFilterBefore(new JwtAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);



    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
