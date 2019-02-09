package de.tosken.dockerui.config;

import de.tosken.dockerui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * dockerui
 * User: Sebastian
 * Date: 19.05.2018
 * Time: 11:22
 */
@Configuration
public class AuthConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public AuthConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new de.tosken.dockerui.auth.AuthenticationManager(passwordEncoder(), userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login*").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/index.xhtml").permitAll()
                .antMatchers("/register.xhtml").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/javax.faces.resource/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .loginPage("/login.xhtml")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/index.xhtml")
                .failureUrl("/login.xhtml?error")
                .and()
                .logout().logoutSuccessUrl("/index.xhtml");
    }
}
