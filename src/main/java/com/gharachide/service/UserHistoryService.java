package com.gharachide.service;

import com.gharachide.domain.history.UserHistory;
import com.gharachide.domain.history.UserHistoryRepository;
import com.gharachide.domain.user.UserRepository;
import com.gharachide.rest.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * The class {@code UserHistoryService} defines services of {@link UserHistory} resource.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@Service
@AllArgsConstructor
public class UserHistoryService {

    private final UserHistoryRepository repository;
    private final UserRepository userRepository;

    @Transactional
    public UserHistory save(final UserHistory userHistory) {
        return repository.save(userHistory);
    }

    public List<UserHistory> getAll() {
        return repository.findAll();
    }

    public List<UserHistory> getByUser(final String email) {
        final Optional<List<UserHistory>> historyOptional = repository.findAllByUser(email);

        return historyOptional.orElseThrow(ResourceNotFoundException::new);
    }

}
