package project.com.Recruitment.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // inte helt säker på hur det fungerar
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll() // tillåter just nu alla användare att gå vart som helst, måste fixas
                // .anyRequest().authenticated() // används när alla användare ska vara authenticated
            )
            .formLogin(form -> form.disable()); // disabla spring security login form:en
        
        return http.build();
    }
}

