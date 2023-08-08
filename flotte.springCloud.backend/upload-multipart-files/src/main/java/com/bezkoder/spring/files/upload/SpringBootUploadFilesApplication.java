package com.bezkoder.spring.files.upload;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bezkoder.spring.files.upload.service.FilesStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication

public class SpringBootUploadFilesApplication {
//implements CommandLineRunner
  @Value("${corsConfiguration.Origins}")
  private String origins;


//  @Resource
//  FilesStorageService storageService;

  public static void main(String[] args) {
    SpringApplication.run(SpringBootUploadFilesApplication.class, args);

  }

//  @Override
//  public void run(String... arg) throws Exception {
//    storageService.deleteAll();
//    storageService.init();
//  }
  @Bean
  public CorsFilter corsFilter() {
    System.out.println("============================");
    System.out.println(origins);
    System.out.println("============================");
    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.setAllowedOrigins(Collections.singletonList(origins));
    corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
            "Accept", "token", "Authorization", "Origin, Accept", "X-Requested-With",
            "Access-Control-Request-Method", "Access-Control-Request-Headers"));
    corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "token", "Authorization",
            "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
    corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS","PATCH"));
    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
    return new CorsFilter(urlBasedCorsConfigurationSource);
  }
}
