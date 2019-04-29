package com.gharachide.domain.event;

/**
 * The interface {@code DomainEventListener} defines the contract of domain event listeners.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@FunctionalInterface
public interface DomainEventListener<T> {
    void handle(final T domainEvent);
}