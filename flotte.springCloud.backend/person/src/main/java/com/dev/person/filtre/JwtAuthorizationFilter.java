package com.dev.person.filtre;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dev.person.model.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private static UserClient userClient;

    public JwtAuthorizationFilter(){}
    public JwtAuthorizationFilter(UserClient userClient){
        this.userClient=userClient;
    }

    private Environment environment;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().equals("/refreshToken")) {
            filterChain.doFilter(request, response);
        } else {

            String authorizationToken = request.getHeader("Authorization");
            if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
                 System.out.println(authorizationToken);
                          try {
                    String jwt = authorizationToken.substring(7);
                    System.out.println("jwt: "+jwt);

                    Algorithm algorithm = Algorithm.HMAC256("absdefijklmn12nmlkjifessm");//HS256
                    System.out.println("algorithm: "+algorithm);
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    System.out.println("jwtVerifier: "+jwtVerifier);
                    DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                    System.out.println("decodedJWT"+decodedJWT);
                    String usergetId = decodedJWT.getSubject();
                    System.out.println(usergetId);
                              String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                              Collection<GrantedAuthority> authorities = new ArrayList<>();
                              for (String r : roles) {
                                  authorities.add(new SimpleGrantedAuthority(r));
                              }
                              UsernamePasswordAuthenticationToken authenticationToken =
                                      new UsernamePasswordAuthenticationToken(usergetId, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    filterChain.doFilter(request, response);
                              System.out.println(SecurityContextHolder.getContext().getAuthentication());


                } catch (Exception e) {
                    response.setHeader("error-message", e.getMessage());
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);

                }
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

}
