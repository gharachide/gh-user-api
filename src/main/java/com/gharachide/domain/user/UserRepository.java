package com.gharachide.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface {@code UserRepository} is the repository of {@link User} resource.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
public interface UserRepository extends JpaRepository<User, String> {
}
