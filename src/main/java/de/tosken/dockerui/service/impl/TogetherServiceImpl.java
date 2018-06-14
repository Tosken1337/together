package de.tosken.dockerui.service.impl;

import de.tosken.dockerui.persistance.model.Together;
import de.tosken.dockerui.persistance.model.User;
import de.tosken.dockerui.persistance.repository.TogetherRepository;
import de.tosken.dockerui.service.TogetherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    public Together findByRef(final String ref) {
        return repository.findByRef(ref);
    }

    @Override
    public List<Together> findByUser(User user) {
        return repository.findByCreatorOrderById(user);
    }

    @Override
    public void delete(final Together together) {
        final Together managedTogether = findByRef(together.getRef());
        repository.delete(managedTogether);
        repository.flush();
    }
}
