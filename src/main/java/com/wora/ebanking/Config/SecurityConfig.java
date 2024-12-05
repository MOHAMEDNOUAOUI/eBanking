package com.wora.ebanking.Config;

import com.wora.ebanking.Exceptions.CustomAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig{

    private final CustomUserDetailsService userDetailsService;
    private final AccessDeniedHandler accessDeniedHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;


    @Bean
    @Profile("test")
    public SecurityFilterChain testFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .anyRequest().permitAll());
                return http.build();
    }

    @Bean
    @Profile("!test")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http
               .csrf(csrf -> csrf.disable())
               .httpBasic(HttpBasic -> HttpBasic.authenticationEntryPoint(customAuthenticationEntryPoint))
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers("/api/users/register" , "/api/contact" , "/api/notices").permitAll()
                       .requestMatchers("/api/users/").hasRole("ADMIN")
                       .requestMatchers("/api/myLoans" , "/api/myAccount" , "/api/myCards" , "/api/myBalance")
                       .hasAnyRole("USER")
                       .anyRequest().authenticated()
               )
               .exceptionHandling(exception -> exception
                       .accessDeniedHandler(accessDeniedHandler));
        return http.build();
    }


   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
   }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
