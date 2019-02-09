package de.tosken.dockerui.auth;

import de.tosken.dockerui.persistence.model.User;
import de.tosken.dockerui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * dockerui
 * User: Sebastian
 * Date: 19.05.2018
 * Time: 21:00
 */
public class AuthenticationManager implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @Autowired
    public AuthenticationManager(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String email = ((String) authentication.getPrincipal());
        final String password = ((String) authentication.getCredentials());

        final User user = userService.findByEMail(email);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return AuthenticatedUser.fromUser(user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
