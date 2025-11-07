package co.edu.uco.HumanSolution.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Permitir credenciales
        config.setAllowCredentials(true);

        // Permitir solicitudes desde el frontend Angular
        config.addAllowedOrigin("http://localhost:4200");

        // Permitir todos los headers
        config.addAllowedHeader("*");

        // Permitir todos los métodos HTTP
        config.addAllowedMethod("*");

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}