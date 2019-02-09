package de.tosken.dockerui.service;

import de.tosken.dockerui.persistence.dao.UserRepository;
import de.tosken.dockerui.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEMail(final String eMail) {
        return userRepository.getByEmail(eMail);
    }
}
