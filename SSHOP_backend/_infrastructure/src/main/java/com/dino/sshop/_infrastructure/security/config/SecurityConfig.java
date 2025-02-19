package com.dino.sshop._infrastructure.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

//    private final String[] PUBLIC_GET_ENDPOINTS = {
//            "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**",
//            "/media/**",
//            "/api/v1/category/**",
//            "/admin/api/v1/access/test",
//
//    };
//
//    private final String[] PUBLIC_POST_ENDPOINTS = {
//            "/admin/api/v1/access/login",
//            "/seller/api/v1/access/login",
//            "/seller/api/v1/account/create",
//            "/seller/api/v1/access/signup",
//            "/api/v1/access/login",
//            "/api/v1/access/login/oauth2",
//            "/api/v1/media/**",
//
//
//    };

    private final String[] PUBLIC_ENDPOINTS = {
            //SWAGGER//
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/v3/api-docs/**",
            //MEDIA//
            "/media/**",
            "/api/v1/media/**",
            //CATEGORY//
            "/api/v1/category/**",
            //ACCESS//
            "/admin/api/v1/access/login/username",
            "/seller/api/v1/access/login/username",
            "/seller/api/v1/access/login/oauth2",
            "/seller/api/v1/access/signup/username",
            "/seller/api/v1/access/signup/oauth2",
            "/api/v1/access/login/username",
            "/api/v1/access/login/oauth2",
            "/api/v1/access/signup/username",
            "/api/v1/access/signup/oauth2",
            //ACCOUNT//
            "/seller/api/v1/account/create",
    };

    @Value("${jwt.access.secret-key}")
    private String ACCESS_SECRET_KEY; //todo: change to the security infra service

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->
                        request
                                .requestMatchers(this.PUBLIC_ENDPOINTS).permitAll()
//                        .requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
                                .anyRequest().authenticated()
        );

        httpSecurity.oauth2ResourceServer(oauth2 ->
                oauth2
                        //config jwt: decode, convert authentication
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .decoder(jwtDecoder())
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                        //handle exception
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.cors(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.ACCESS_SECRET_KEY.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    /**
     * convert "SCOPE_" to "ROLE_" of each JwtGrantedAuthorities inside jwtAuthentication
     *
     * @note: SCOPE_ of JwtGrantedAuthorities is automatically mapped from the scope claim of jwt payload by Spring Security
     */
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    /**
     * PasswordEncoder and provide the encode, match methods
     *
     * @note: PasswordEncoder uses BCrypt Algorithm
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
