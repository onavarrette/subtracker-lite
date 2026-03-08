package com.github.onavarrette.subtracker.controller;

import com.github.onavarrette.subtracker.dto.SubscriptionDto;
import com.github.onavarrette.subtracker.entity.Subscription;
import com.github.onavarrette.subtracker.service.SubscriptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Subscription createSubscription(@Valid @RequestBody SubscriptionDto dto) {
        return subscriptionService.createSubscription(dto);
    }

    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    @GetMapping("/{id}")
    public Subscription getSubscriptionById(@PathVariable Long id) {
        return subscriptionService.getSubscriptionById(id);
    }

    @PutMapping("/{id}")
    public Subscription updateSubscription(@PathVariable Long id, @Valid @RequestBody SubscriptionDto dto) {
        return subscriptionService.updateSubscription(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
    }

    @GetMapping("/total-cost")
    public BigDecimal getTotalCost() {
        return subscriptionService.getTotalCost();
    }
}
