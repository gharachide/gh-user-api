package com.gharachide.domain.history;

import com.gharachide.domain.event.DomainEventListener;
import com.gharachide.domain.user.User;
import com.gharachide.domain.user.UserCreatedEvent;
import com.gharachide.service.UserHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * The class {@code UserCreatedListener} listens to domain events of {@link User} resource.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@Component
@AllArgsConstructor
public class UserCreatedListener implements DomainEventListener<UserCreatedEvent> {

    private final UserHistoryService service;

    @Override
    public void handle(UserCreatedEvent domainEvent) {
        service.save(new UserHistory(domainEvent.getSource().getEmail(),
                domainEvent.getType(), Instant.now()));
    }
}
