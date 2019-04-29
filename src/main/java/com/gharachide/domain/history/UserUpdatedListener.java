package com.gharachide.domain.history;

import com.gharachide.domain.event.DomainEventListener;
import com.gharachide.domain.user.User;
import com.gharachide.domain.user.UserUpdatedEvent;
import com.gharachide.service.UserHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * The class {@code UserUpdatedListener} listens to domain events of {@link User} resource.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@Component
@AllArgsConstructor
public class UserUpdatedListener implements DomainEventListener<UserUpdatedEvent> {

    private final UserHistoryService service;

    @Override
    public void handle(UserUpdatedEvent domainEvent) {
        service.save(new UserHistory(domainEvent.getSource().getEmail(),
                domainEvent.getType(), Instant.now()));
    }
}
