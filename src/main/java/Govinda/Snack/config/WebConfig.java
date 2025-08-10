package Govinda.Snack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // CORS setup
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }

    // In-memory admin user for testing
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails adminUser = User.withUsername("sunil")
                .password("{noop}SunilDeshmukh@6969") // {noop} for plain-text (dev only)
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(adminUser);
    }

    // Spring Security configuration
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> {
                    // Public endpoints
                    auth.requestMatchers(
                            new AntPathRequestMatcher("/"),
                            new AntPathRequestMatcher("/index.html"),
                            new AntPathRequestMatcher("/order.html"),
                            new AntPathRequestMatcher("/login.html"),
                            new AntPathRequestMatcher("/assets/**"),
                            new AntPathRequestMatcher("/api/user/**")
                    ).permitAll();

                    // Admin-only pages
                    auth.requestMatchers(
                            new AntPathRequestMatcher("/admin/**"),
                            new AntPathRequestMatcher("/api/admin/**")
                    ).hasRole("ADMIN");

                    // Any other request needs authentication
                    auth.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/login.html")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/dashboard.html", true)
                        .failureUrl("/login.html?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                        .logoutSuccessUrl("/")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // Disabled for dev; enable in production

        return http.build();
    }
}
