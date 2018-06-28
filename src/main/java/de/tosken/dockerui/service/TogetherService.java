package de.tosken.dockerui.service;

import de.tosken.dockerui.persistance.model.Together;
import de.tosken.dockerui.persistance.model.TogetherItem;
import de.tosken.dockerui.persistance.model.User;

import java.util.List;

/**
 * dockerui
 * User: Sebastian
 * Date: 31.05.2018
 * Time: 09:37
 */
public interface TogetherService {
    Together save(Together together);

    Together findByRef(String ref);
    List<Together> findByUser(User user);

    void delete(Together together);

    void enrollForItem(TogetherItem item, User user);

    void enrollOutForItem(TogetherItem item, User user);

    void addNewItem(Together together, User creator, String value);
}
