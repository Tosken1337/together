package de.tosken.dockerui.service;

import de.tosken.dockerui.persistance.model.Together;

/**
 * dockerui
 * User: Sebastian
 * Date: 31.05.2018
 * Time: 09:37
 */
public interface TogetherService {
    Together save(Together together);
}
