package de.tosken.dockerui.config;

import de.tosken.dockerui.auth.RememberMeUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
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
        return new de.tosken.dockerui.auth.AuthenticationManager();
    }

    /**
     * UserDetailsService is only needed for remember me authentication.
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new RememberMeUserDetailsService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login*").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/index.xhtml").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/javax.faces.resource/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .rememberMe().key("uniqueAndSecret").and()
                .formLogin()
                .loginPage("/login.xhtml")
                .defaultSuccessUrl("/index.xhtml")
                .failureUrl("/login.xhtml?error")
                .and()
                .logout().logoutSuccessUrl("/index.xhtml");
    }
}