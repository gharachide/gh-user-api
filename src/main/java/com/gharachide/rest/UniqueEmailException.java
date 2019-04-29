package com.gharachide.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The class {@code UniqueEmailException} represents a bad HTTP request and returns 409.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "E-mail already registered")
public class UniqueEmailException extends RuntimeException {

}
