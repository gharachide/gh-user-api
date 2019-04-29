package com.gharachide.domain.history;

import com.gharachide.domain.event.DomainEventType;
import com.gharachide.domain.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

/**
 * The class {@code UserHistory} represents the log of operations on {@link User} resource.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@Data
@Entity
public class UserHistory extends History {

    @Id
    @GeneratedValue
    private final Long id;

    @Column
    @NotNull
    private final String user;

    public UserHistory(final String user, final DomainEventType operation, final Instant time) {
        super(operation, time);
        this.id = null;
        this.user = user;
    }

    private UserHistory() {
        this(null, null, null);
    }
}
