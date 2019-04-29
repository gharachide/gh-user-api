package com.gharachide.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gharachide.domain.event.DomainEventType;
import com.gharachide.domain.user.Role;
import com.gharachide.domain.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The class {@code UserHistoryControllerTest} tests available endpoints
 * of {@link UserHistoryController}.
 *
 * @author Gabriel Harachide
 * @version 1.0
 * @since 29/04/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserHistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        this.mockMvc.perform(delete("/users"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void add() throws Exception {
        final String email = "user@email.com";
        final User user = new User(email,
                "pass", "user", "11111", Role.ADMIN);
        final String json = mapper.writeValueAsString(user);

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(email)));

        this.mockMvc.perform(get("/users/history/" + email))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(DomainEventType.CREATED.toString())));
    }

    @Test
    public void getAll() throws Exception {
        this.mockMvc.perform(get("/users/history"))
                .andExpect(status().isOk());
    }

    @Test
    public void getById() throws Exception {
        final String email = "user@email.com";
        final User user = new User(email,
                "pass", "user", "11111", Role.ADMIN);
        final String json = mapper.writeValueAsString(user);

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/users/" + email))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(email)));

        this.mockMvc.perform(get("/users/history/" + email))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(DomainEventType.VIEWED.toString())));
    }

    @Test
    public void getByInvalidUser() throws Exception {
        this.mockMvc.perform(get("/users/history/" + "invalid@email.com"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void update() throws Exception {
        final String email = "user@email.com";
        final User user = new User(email,
                "pass", "user", "11111", Role.ADMIN);
        final String json = mapper.writeValueAsString(user);

        this.mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk());

        final User updatedUser = new User(email,
                "new pass", "new user", "22222", Role.ADMIN);
        final String updatedJson = mapper.writeValueAsString(updatedUser);

        this.mockMvc.perform(put("/users/" + email)
                .contentType(MediaType.APPLICATION_JSON).content(updatedJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(updatedUser.getContact())));

        this.mockMvc.perform(get("/users/history/" + email))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(DomainEventType.UPDATED.toString())));
    }
}