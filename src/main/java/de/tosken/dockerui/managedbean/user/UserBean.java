package de.tosken.dockerui.managedbean.user;

import de.tosken.dockerui.auth.AuthenticatedUser;
import de.tosken.dockerui.persistance.model.User;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

/**
 * dockerui
 * User: Sebastian
 * Date: 20.05.2018
 * Time: 21:54
 */
@Named
@SessionScoped
public class UserBean implements Serializable {

    public UserBean() {
    }

    public String getUserName() {
        return getCurrentUser().map(AuthenticatedUser::getName).orElse("");
    }

    public Optional<AuthenticatedUser> getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AuthenticatedUser) {
            return Optional.of(((AuthenticatedUser) authentication));
        } else if (authentication instanceof RememberMeAuthenticationToken) {
            final AuthenticatedUser details = (AuthenticatedUser) authentication.getPrincipal();
            return Optional.of(details);
        } else {
            return Optional.empty();
        }
    }

    public User getCurrentUserModel() {
        return ((User) getCurrentUser().get().getDetails());
    }

    public boolean isAuthenticated() {
        return getCurrentUser().isPresent();
    }

    public boolean isThisMe(final User user) {
        if (!isAuthenticated())
            return false;

        return getCurrentUserModel().equals(user);
    }
}
