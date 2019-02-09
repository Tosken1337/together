package de.tosken.dockerui.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * dockerui
 * User: Sebastian
 * Date: 19.05.2018
 * Time: 11:07
 */
@Configuration
@EntityScan(basePackages = "de.tosken.dockerui.persistence.model")
@EnableJpaRepositories("de.tosken.dockerui.persistence.dao")
@EnableTransactionManagement
public class DatabaseConfig {
}
