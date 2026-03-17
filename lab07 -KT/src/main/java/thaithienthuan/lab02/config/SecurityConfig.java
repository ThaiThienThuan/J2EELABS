package thaithienthuan.lab02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import thaithienthuan.lab02.service.CustomOAuth2UserService;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Autowired
    private CustomOAuth2UserService customOAuth2UserService;
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

@Bean
public AuthenticationManager authenticationManager(
        AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable())

        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
            .requestMatchers("/enroll/**").hasAuthority("ROLE_STUDENT")
            .requestMatchers("/courses").permitAll()
            .requestMatchers("/courses/search").permitAll()

            .requestMatchers(
                    "/",
                    "/home",
                    "/login",
                    "/register",
                    "/oauth2/**",
                    "/error",
                    "/css/**",
                    "/js/**",
                    "/images/**",
                    "/api/**"
            ).permitAll()

            .anyRequest().authenticated()
        )

        .formLogin(form -> form
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .usernameParameter("username")
            .passwordParameter("password")
            .defaultSuccessUrl("/home", true)
            .failureUrl("/login?error=true")
        )

        .oauth2Login(oauth -> oauth
                .loginPage("/login") 
                .userInfoEndpoint(userInfo -> userInfo
                    .userService(customOAuth2UserService)
                )
                .defaultSuccessUrl("/home", true)
            )

        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .invalidateHttpSession(true)
        );

    return http.build();
}

}
