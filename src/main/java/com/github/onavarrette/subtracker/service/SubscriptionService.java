package com.github.onavarrette.subtracker.service;

import com.github.onavarrette.subtracker.dto.SubscriptionDto;
import com.github.onavarrette.subtracker.entity.Subscription;
import com.github.onavarrette.subtracker.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Transactional
    public Subscription createSubscription(SubscriptionDto dto) {
        Subscription subscription = new Subscription();
        subscription.setName(dto.name());
        subscription.setPrice(dto.price());
        subscription.setRenewalDate(dto.renewalDate());
        return subscriptionRepository.save(subscription);
    }

    @Transactional(readOnly = true)
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found with id: " + id));
    }

    @Transactional
    public Subscription updateSubscription(Long id, SubscriptionDto dto) {
        Subscription existing = getSubscriptionById(id);
        existing.setName(dto.name());
        existing.setPrice(dto.price());
        existing.setRenewalDate(dto.renewalDate());
        return subscriptionRepository.save(existing);
    }

    @Transactional
    public void deleteSubscription(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new IllegalArgumentException("Subscription not found with id: " + id);
        }
        subscriptionRepository.deleteById(id);
    }

    @Transactional
    public BigDecimal getTotalCost() {
        return subscriptionRepository.findAll().stream()
                .map(Subscription::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
