package de.tosken.dockerui.user;

import de.tosken.dockerui.persistance.model.User;
import de.tosken.dockerui.persistance.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * dockerui
 * User: Sebastian
 * Date: 19.05.2018
 * Time: 20:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername() {
        final User user = userRepository.findByUsername("tosken");
        assertThat(user.geteMail(), equalTo("tosken@gmail.com"));
    }
}