package com.gharachide.domain.history;

import com.gharachide.domain.event.DomainEventListener;
import com.gharachide.domain.user.User;
import com.gharachide.domain.user.UserViewedEvent;
import com.gharachide.service.UserHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

/**
 * The class {@code UserViewedListener} listens to domain events of {@link User} resource.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@Component
@AllArgsConstructor
public class UserViewedListener implements DomainEventListener<UserViewedEvent> {

    private final UserHistoryService service;

    @Override
    public void handle(UserViewedEvent domainEvent) {
        service.save(new UserHistory(domainEvent.getSource().getEmail(),
                domainEvent.getType(), Instant.now()));
    }
}
