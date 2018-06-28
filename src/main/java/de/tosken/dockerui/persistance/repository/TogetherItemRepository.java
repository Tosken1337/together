package de.tosken.dockerui.persistance.repository;

import de.tosken.dockerui.persistance.model.TogetherItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * together
 * User: Sebastian
 * Date: 23.06.2018
 * Time: 11:33
 */
public interface TogetherItemRepository extends JpaRepository<TogetherItem, Integer> {
}
