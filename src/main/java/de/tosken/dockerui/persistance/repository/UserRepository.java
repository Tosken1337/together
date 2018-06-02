package de.tosken.dockerui.persistance.repository;

import de.tosken.dockerui.persistance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * dockerui
 * User: Sebastian
 * Date: 19.05.2018
 * Time: 17:36
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEMail(String email);
}
