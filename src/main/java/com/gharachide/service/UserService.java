package com.gharachide.service;

import com.gharachide.domain.event.DomainEventPublisher;
import com.gharachide.domain.user.*;
import com.gharachide.rest.ResourceNotFoundException;
import com.gharachide.rest.UniqueEmailException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * The class {@code UserService} defines services of {@link User} resource.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final DomainEventPublisher domainEventPublisher;

    @Transactional
    public User save(final User user) {
        if (repository.existsById(user.getEmail())) {
            throw new UniqueEmailException();
        }

        encryptPassword(user);
        final User createdUser = repository.save(user);
        domainEventPublisher.publish(new UserCreatedEvent(createdUser));

        return createdUser;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User getById(final String id) {
        final Optional<User> userOptional = repository.findById(id);

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException();
        }

        final User userFromDB = userOptional.get();
        domainEventPublisher.publish(new UserViewedEvent(userFromDB));

        return userFromDB;
    }

    @Transactional
    public User update(final String id, final User user) {
        final Optional<User> userOptional = repository.findById(id);

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException();
        }

        final User userFromDB = userOptional.get();
        userFromDB.update(user);
        encryptPassword(userFromDB);

        final User updatedUser = repository.save(userFromDB);
        domainEventPublisher.publish(new UserUpdatedEvent(updatedUser));

        return updatedUser;
    }

    @Transactional
    public void deleteById(final String id) {
        final Optional<User> userOptional = repository.findById(id);

        if (!userOptional.isPresent()) {
            throw new ResourceNotFoundException();
        }

        repository.delete(userOptional.get());
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    private void encryptPassword(final User user) {
        if (!StringUtils.isEmpty(user.getPassword())) {
            user.encryptPassword(passwordEncoder.encode(user.getPassword()));
        }
    }
}