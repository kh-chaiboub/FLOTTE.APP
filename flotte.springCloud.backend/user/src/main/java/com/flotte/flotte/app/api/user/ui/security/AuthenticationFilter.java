package com.flotte.flotte.app.api.user.ui.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flotte.flotte.app.api.user.ui.dto.UserDto;
import com.flotte.flotte.app.api.user.ui.model.LoginRequest;
import com.flotte.flotte.app.api.user.ui.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.*;

import static org.springframework.http.HttpStatus.OK;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;
    private Environment environment;

    public AuthenticationFilter(UserService userService, Environment environment, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.environment = environment;
        this.setAuthenticationManager(authenticationManager);

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest creds = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginRequest.class);
//            creds.setEmail(creds.getUsername());
            creds.setUsername(creds.getUsername());
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
//                    creds.getEmail(),
                    creds.getUsername(),
                    creds.getPassword(),
                    new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected  void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();


        String userName = ((User) authResult.getPrincipal()).getUsername();
        UserDto userDto = userService.getUserDetailsByUserName(userName);
     Algorithm algo1= Algorithm.HMAC256(environment.getProperty("token.seecret"));
System.out.println(environment.getProperty("token.expiration_time"));
        System.out.println(environment.getProperty("token.seecret"));
//        String token = Jwts.builder()
//                .setSubject(userDto.getUserId())
//
//                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
//                .signWith(SignatureAlgorithm.HS256, environment.getProperty("token.seecret"))
//                .compact();
        String token = JWT.create()
                        .withSubject(userDto.getId())
                        .withExpiresAt(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
//                .withClaim("roles",Arrays.asList("ADMIN","USER"))
//                .withClaim("UserId",userDto.getUserId())
                .withClaim("roles", Arrays.asList(userDto.getRoles()))
                        .sign(algo1);

System.out.println(token);
        response.addHeader("token", token);
        response.addHeader("userId", userDto.getUserId());
        response.addHeader("userEmail", userDto.getEmail());

        response.getOutputStream().println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userDto));
        //response.getWriter().println(userDto);


    }


}
