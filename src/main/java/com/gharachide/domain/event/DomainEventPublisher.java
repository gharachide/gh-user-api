package com.gharachide.domain.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * The class {@code DomainEventPublisher} represents...
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@Component
@AllArgsConstructor
public class DomainEventPublisher {

    private final Map<DomainEventType, List<DomainEventListener>> listeners;

    public void publish(final DomainEvent domainEvent) {
        if (domainEvent != null) {
            listeners.get(domainEvent.getType())
                    .forEach(listener -> listener.handle(domainEvent));
        }
    }
}
