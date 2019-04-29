package com.gharachide.rest;

import com.gharachide.domain.user.User;
import com.gharachide.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * The class {@code UserController} defines the REST API for {@link User} resource.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@AllArgsConstructor
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService service;

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @PostMapping
    public User add(@Valid @RequestBody final User user) {
        return service.save(user);
    }

    @GetMapping("/{email}")
    public User getById(@PathVariable final String email) {
        return service.getById(email);
    }

    @PutMapping("/{email}")
    public User update(@PathVariable final String email,
                                   @Valid @RequestBody final User user) {
        return service.update(email, user);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> delete(@PathVariable final String email) {
        service.deleteById(email);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete() {
        service.deleteAll();

        return ResponseEntity.noContent().build();
    }
}