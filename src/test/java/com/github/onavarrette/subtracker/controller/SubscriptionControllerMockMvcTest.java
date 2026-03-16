package com.github.onavarrette.subtracker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;

import com.github.onavarrette.subtracker.entity.Subscription;
import com.github.onavarrette.subtracker.service.SubscriptionService;

@WebMvcTest(SubscriptionController.class)
class SubscriptionControllerMockMvcTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    SubscriptionService subscriptionService;

    RestTestClient client;

    @BeforeEach
    void setUp() {
        client = RestTestClient.bindTo(mvc).build();
    }

    @Test
    void testGetAllSubscriptions() {
        when(subscriptionService.getAllSubscriptions()).thenReturn(List.of(
                new Subscription(9L, "Netflix", BigDecimal.valueOf(12.99), LocalDate.now())));

        List<Subscription> subscriptions = client.get()
                .uri("/api/subscriptions")
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<List<Subscription>>() {
                })
                .returnResult()
                .getResponseBody();

        assertEquals(1, subscriptions.size());
        assertEquals("Netflix", subscriptions.get(0).getName());
    }
}
