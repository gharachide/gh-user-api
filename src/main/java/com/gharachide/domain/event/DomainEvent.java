package com.gharachide.domain.event;

/**
 * The class {@code DomainEvent} represents a domain event.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
public abstract class DomainEvent<T> {
    public abstract T getSource();
    public abstract DomainEventType getType();
}
