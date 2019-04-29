package com.gharachide.config;

import com.gharachide.domain.event.DomainEventListener;
import com.gharachide.domain.event.DomainEventType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * The class {@code DomainConfig} defines domain configurations...
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@Configuration
public class DomainConfig {

    @Bean
    public Map<DomainEventType, List<DomainEventListener>> listeners(
            @Qualifier("userCreatedListener") final DomainEventListener userCreatedListener,
            @Qualifier("userViewedListener") final DomainEventListener userViewedListener,
            @Qualifier("userUpdatedListener") final DomainEventListener userUpdatedListener) {
        final Map<DomainEventType, List<DomainEventListener>> map = new EnumMap<>(DomainEventType.class);
        map.put(DomainEventType.CREATED, Arrays.asList(userCreatedListener));
        map.put(DomainEventType.VIEWED, Arrays.asList(userViewedListener));
        map.put(DomainEventType.UPDATED, Arrays.asList(userUpdatedListener));

        return map;
    }
}
