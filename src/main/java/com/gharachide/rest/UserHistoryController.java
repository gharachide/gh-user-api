package com.gharachide.rest;

import com.gharachide.domain.history.UserHistory;
import com.gharachide.service.UserHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The class {@code UserHistoryController} defines the REST API for {@link UserHistory} resource.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@AllArgsConstructor
@RestController
@RequestMapping("users/history")
public class UserHistoryController {

    private final UserHistoryService service;

    @GetMapping
    public List<UserHistory> getAll() {
        return service.getAll();
    }

    @GetMapping("/{email}")
    public List<UserHistory> getAllByUser(@PathVariable final String email) {
        return service.getByUser(email);
    }
}