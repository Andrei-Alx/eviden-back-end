package nl.fontys.atosgame.Authentication.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("nl.fontys.atosgame.Authentication") // Scan your base package
@EnableJpaRepositories("nl.fontys.atosgame.Authentication.repository") // Scan repository package
@EntityScan("nl.fontys.atosgame.Authentication.model") // Scan entity package
public class AppConfig {
    // Configuration class definition
}