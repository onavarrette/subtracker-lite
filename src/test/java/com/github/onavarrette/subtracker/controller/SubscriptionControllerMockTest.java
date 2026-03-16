package com.github.onavarrette.subtracker.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;

import com.github.onavarrette.subtracker.entity.Subscription;
import com.github.onavarrette.subtracker.service.SubscriptionService;

class SubscriptionControllerMockTest {

    RestTestClient client;
    SubscriptionService subscriptionService;

    @BeforeEach
    void setup() {
        subscriptionService = Mockito.mock(SubscriptionService.class);
        Mockito.when(subscriptionService.getAllSubscriptions()).thenReturn(List.of(
                new Subscription(9L, "Netflix", BigDecimal.valueOf(12.99), LocalDate.now())));

        client = RestTestClient.bindToController(new SubscriptionController(subscriptionService)).build();
    }

    @Test
    void testGetAllSubscriptions() {
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
