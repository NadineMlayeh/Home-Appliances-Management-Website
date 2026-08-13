package org.sid.electromenager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        @SuppressWarnings("removal")
        LogoutConfigurer<HttpSecurity> permitAll = http
            .authorizeRequests()
            .requestMatchers(
                "/",
                "/home",
                "/login",
                "/aboutus",
                "/contact",
                "/ListArticles",
                "/css/**",
                "/js/**",
                "/images/**",
                "/img/**",
                "/assets/**",
                "/assets/images/**",
                "/assets/img/**",
                "/fonts/**",
                "/webjars/**",
                "/mail/**",
                "/logo/**",
                "/favicon.ico"
            ).permitAll() // Allow access to static resources and public pages
                .requestMatchers(
                    new AntPathRequestMatcher("/**/*.png", null, false),
                    new AntPathRequestMatcher("/**/*.jpg", null, false),
                    new AntPathRequestMatcher("/**/*.jpeg", null, false),
                    new AntPathRequestMatcher("/**/*.gif", null, false),
                    new AntPathRequestMatcher("/**/*.jfif", null, false),
                    new AntPathRequestMatcher("/**/*.css", null, false)
                ).permitAll() // Static images/css anywhere (case-insensitive); avoids the PathPattern error on "/**/*.ext"
                .requestMatchers("/admin/**", "/addArticle", "/addAchat", "/addClient").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home", true) // Simplify to check
                .permitAll()
                .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build());
        return manager;
    }
}
