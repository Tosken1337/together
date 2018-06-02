package de.tosken.dockerui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * dockerui
 * User: Sebastian
 * Date: 19.05.2018
 * Time: 11:07
 */
@Configuration
@EnableJpaRepositories("de.tosken.dockerui")
public class DatabaseConfig {
}
