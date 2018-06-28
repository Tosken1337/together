package de.tosken.dockerui.service.impl;

import de.tosken.dockerui.persistance.model.Together;
import de.tosken.dockerui.persistance.model.TogetherItem;
import de.tosken.dockerui.persistance.model.User;
import de.tosken.dockerui.persistance.repository.TogetherItemRepository;
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

    @Autowired
    private TogetherItemRepository itemRepository;

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

    @Override
    public void enrollForItem(TogetherItem item, User user) {
        item.setResponsible(user);
        itemRepository.saveAndFlush(item);
    }

    @Override
    public void enrollOutForItem(TogetherItem item, User user) {
        if (!item.hasResponsiblePerson() || !item.getResponsible().equals(user)) {
            throw new IllegalArgumentException("Unable to enroll out for item. User is not the responsible person");
        }

        item.setResponsible(null);
        itemRepository.saveAndFlush(item);
    }

    @Override
    public void addNewItem(Together together, User creator, String value) {
        final TogetherItem newItem = new TogetherItem();
        newItem.setCreator(creator);
        newItem.setTitle(value);
        together.addItem(newItem);
        repository.saveAndFlush(together);
    }
}
