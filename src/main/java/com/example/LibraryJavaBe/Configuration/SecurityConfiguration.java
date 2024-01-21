package com.example.LibraryJavaBe.Configuration;

import com.example.LibraryJavaBe.authService.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final  JwtAuthenticationFilter jwtAuthFilter;
    private final  AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    private static final String[] AUTH_WHITELIST = {
            "/auth/**",
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/api/v1/book/**",
          "/api/v1/category/**",
            "/api/v1/Cart/**"

    };

    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(crfs->crfs.disable())

                .authorizeHttpRequests(
                        rq->rq.requestMatchers(AUTH_WHITELIST)
                                .permitAll().anyRequest()
                                .authenticated());
http.sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)

);
http.authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
           http .logout(logout ->
                logout.logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
        )
;

        return http.build();
    }
//    @Bean
// CorsConfigurationSource corsConfigurationSource() {
//    CorsConfiguration configuration = new CorsConfiguration();
//     configuration.setAllowedOrigins(List.of("http://localhost:8085"));
//     configuration.addAllowedOrigin("*");
//      configuration.setAllowedMethods(List.of("GET","POST"));
//    configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));
//        configuration.addAllowedOrigin("http://localhost:5173"); // Thay đổi port nếu ứng dụng React của bạn chạy trên cổng khác
//
//        configuration.addAllowedMethod("*");
//        configuration.addAllowedHeader("*");
//    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//  source.registerCorsConfiguration("/**",configuration);
//
//   return source;
//}
@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8085", "http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // Nếu cần sử dụng credentials (ví dụ: cookie), hãy đặt true

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }
}
}
