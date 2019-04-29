package com.gharachide.domain.user;

import com.gharachide.domain.event.DomainEvent;
import com.gharachide.domain.event.DomainEventType;
import lombok.AllArgsConstructor;

/**
 * The class {@code UserUpdatedEvent} represents the domain event when an user is updated.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@AllArgsConstructor
public class UserUpdatedEvent extends DomainEvent<User> {

    private final User user;

    @Override
    public User getSource() {
        return user;
    }

    @Override
    public DomainEventType getType() {
        return DomainEventType.UPDATED;
    }
}
