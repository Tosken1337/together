package de.tosken.dockerui.auth;

import de.tosken.dockerui.persistance.model.User;
import de.tosken.dockerui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * dockerui
 * User: Sebastian
 * Date: 21.05.2018
 * Time: 17:11
 */
public class RememberMeUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return AuthenticatedUser.fromUser(user);
    }
}
