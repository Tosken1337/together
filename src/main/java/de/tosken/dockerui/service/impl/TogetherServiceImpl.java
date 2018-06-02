package de.tosken.dockerui.service.impl;

import de.tosken.dockerui.persistance.model.Together;
import de.tosken.dockerui.persistance.repository.TogetherRepository;
import de.tosken.dockerui.service.TogetherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * dockerui
 * User: Sebastian
 * Date: 31.05.2018
 * Time: 09:38
 */
@Service
@Transactional
public class TogetherServiceImpl implements TogetherService {
    @Autowired
    private TogetherRepository repository;

    @Override
    public Together save(final Together together) {
        return repository.saveAndFlush(together);
    }
}
