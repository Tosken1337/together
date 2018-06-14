package de.tosken.dockerui.service;

import de.tosken.dockerui.persistance.model.User;

/**
 * dockerui
 * User: Sebastian
 * Date: 19.05.2018
 * Time: 17:40
 */
public interface UserService {
    User findByUsername(final String username);
    User findByEmail(final String email);
    User registerNewUser(final String username, final String email, final String password);
}
