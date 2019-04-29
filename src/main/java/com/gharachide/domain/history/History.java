package com.gharachide.domain.history;

import com.gharachide.domain.event.DomainEventType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * The class {@code History} represents the abstraction of a resource history.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@Getter
@AllArgsConstructor
@MappedSuperclass
public abstract class History {

    @Column
    @NotNull
    private final DomainEventType operation;

    @Column
    @NotNull
    private final Instant time;
}
