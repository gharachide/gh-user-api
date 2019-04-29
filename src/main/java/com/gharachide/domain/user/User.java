package com.gharachide.domain.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * The class {@code User} represents a user resource.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@Entity
@AllArgsConstructor
@EqualsAndHashCode(of = "email")
@Getter
public class User {

    @Id
    @Email
    private final String email;

    @Column
    @NotNull
    private String password;

    @Column
    @NotNull
    private String name;

    @Column
    private String contact;

    @Column
    @NotNull
    private Role role;

    private User() {
        this(null, null, null, null, null);
    }

    public void update(final User user) {
        this.name = user.getName();
        this.contact = user.getContact();
    }

    public void encryptPassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }
}