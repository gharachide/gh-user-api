package com.gharachide.service;

import com.gharachide.domain.event.DomainEventType;
import com.gharachide.domain.history.UserHistory;
import com.gharachide.domain.user.Role;
import com.gharachide.domain.user.User;
import com.gharachide.rest.ResourceNotFoundException;
import com.gharachide.rest.UniqueEmailException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * The class {@code UserServiceTest} is responsible for {@link UserService} Unit Tests.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Autowired
    private UserHistoryService userHistoryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        this.service.deleteAll();
    }

    @Test
    public void save() throws Exception {
        final String email = "user@email.com";
        final String rawPassword = "pass";
        final User user = new User(email,
                rawPassword, "user", "11111", Role.ADMIN);
        service.save(user);
        final User userFromDB = service.getById(email);
        assertEquals(user, userFromDB);
        assertTrue(passwordEncoder.matches(rawPassword, userFromDB.getPassword()));

        final UserHistory history = userHistoryService.getByUser(email).get(0);
        assertEquals(DomainEventType.CREATED, history.getOperation());
        assertEquals(email, history.getUser());
        assertNotNull(history.getTime());
    }

    @Test(expected = UniqueEmailException.class)
    public void saveExistingEmail() throws Exception {
        final String email = "user@email.com";
        final User user1 = new User(email, "pass1", "user1", "11111", Role.ADMIN);
        service.save(user1);
        final User user2 = new User(email,"pass2", "user2","22222", Role.ADMIN);
        service.save(user2);
    }

    @Test
    public void getAll() throws Exception {
        final User user1 = new User("user1@email.com",
                "pass1", "user1", "11111", Role.ADMIN);
        final User user2 = new User("user2@email.com",
                "pass2", "user2","22222", Role.REGULAR);
        service.save(user1);
        service.save(user2);
        final List<User> list = service.getAll();
        assertTrue(list.contains(user1));
        assertTrue(list.contains(user2));
    }

    @Test
    public void getById() throws Exception {
        final String email = "user@email.com";
        final User user = new User(email,
                "pass", "user", "11111", Role.ADMIN);
        service.save(user);
        final User userFromDB = service.getById(email);
        assertEquals(user, userFromDB);
    }

    @Test
    public void update() throws Exception {
        final String email = "user@email.com";
        final User user = new User(email,
                "pass", "user", "11111", Role.ADMIN);
        service.save(user);
        final User updatedUser = new User(email,
                "pass", "user", "99999", Role.ADMIN);
        service.update(email, updatedUser);
        final User userFromDB = service.getById(email);
        assertEquals(updatedUser.getContact(), userFromDB.getContact());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateInvalidId() throws Exception {
        final String email = "user@email.com";
        final User updatedUser = new User(email,
                "pass", "user", "99999", Role.ADMIN);
        service.update(email, updatedUser);
    }

    @Test
    public void deleteById() throws Exception {
        final String email = "user@email.com";
        final User user = new User(email,
                "pass", "user", "11111", Role.ADMIN);
        service.save(user);
        service.getById(email);
        service.deleteById(email);
        assertFalse(service.getAll().contains(user));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteInvalidId() throws Exception {
        final String email = "user@email.com";
        service.deleteById(email);
    }
}