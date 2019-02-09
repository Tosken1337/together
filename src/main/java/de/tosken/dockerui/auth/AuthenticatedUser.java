package de.tosken.dockerui.auth;

import de.tosken.dockerui.persistence.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * dockerui
 * User: Sebastian
 * Date: 19.05.2018
 * Time: 21:04
 */
public class AuthenticatedUser implements Authentication {

    private String username;

    private String email;

    private List<GrantedAuthority> authorities;

    static Authentication fromUser(User user) {
        final AuthenticatedUser u = new AuthenticatedUser();
        u.username = user.getUsername();
        u.email = user.getEmail();

        u.authorities = user.getRoles().stream()
                .map(role -> {
                    return (GrantedAuthority) role::getName;
                }).collect(Collectors.toList());

        return u;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getDetails() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return "";
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return "";
    }
}
