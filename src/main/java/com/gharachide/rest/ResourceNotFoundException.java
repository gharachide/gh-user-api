package com.gharachide.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The class {@code ResourceNotFoundException} represents a bad HTTP request and returns 404.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
public class ResourceNotFoundException extends RuntimeException {

}
