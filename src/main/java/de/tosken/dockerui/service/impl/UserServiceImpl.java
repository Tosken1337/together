package de.tosken.dockerui.service.impl;

import de.tosken.dockerui.persistance.model.Role;
import de.tosken.dockerui.persistance.model.User;
import de.tosken.dockerui.persistance.repository.UserRepository;
import de.tosken.dockerui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * dockerui
 * User: Sebastian
 * Date: 19.05.2018
 * Time: 17:41
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEMail(email);
    }

    @Override
    @Transactional
    public User registerNewUser(String username, String email, String password) {
        final User user = new User();
        user.seteMail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);

        final Role userRole = userRepository.getDefaultUserRole();
        user.addRole(userRole);

        return userRepository.saveAndFlush(user);
    }
}
