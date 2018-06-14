package de.tosken.dockerui.persistance.repository;

import de.tosken.dockerui.persistance.model.Together;
import de.tosken.dockerui.persistance.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dockerui
 * User: Sebastian
 * Date: 31.05.2018
 * Time: 09:36
 */
@Repository
public interface TogetherRepository extends JpaRepository<Together, Integer> {
    Together findByRef(final String ref);
    List<Together> findByCreatorOrderById(final User user);
}
