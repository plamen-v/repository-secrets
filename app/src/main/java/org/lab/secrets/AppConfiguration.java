package org.lab.secrets;

import org.lab.secrets.core.repository.IRecordRepository;
import org.lab.secrets.core.service.IRecordService;
import org.lab.secrets.repository.RecordRepository;
import org.lab.secrets.service.RecordService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class AppConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }

    @Bean
    public SecurityFilterChain build(HttpSecurity http) throws Exception {
        http.sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/**").permitAll();
                    authorize.anyRequest().authenticated();

                })
                .formLogin((formLogin) -> {
                    formLogin.disable();
                });

        return http.build();
    }


}
