package com.gharachide.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The interface {@code UserHistoryRepository} is the repository of {@link UserHistory} resource.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    Optional<List<UserHistory>> findAllByUser(final String user);
}
