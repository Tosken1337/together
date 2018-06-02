package de.tosken.dockerui.persistance.repository;

import de.tosken.dockerui.persistance.model.Together;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * dockerui
 * User: Sebastian
 * Date: 31.05.2018
 * Time: 09:36
 */
@Repository
public interface TogetherRepository extends JpaRepository<Together, Integer> {
}
