package com.github.onavarrette.subtracker.repository;

import com.github.onavarrette.subtracker.entity.Subscription;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionRepository extends ListCrudRepository<Subscription, Long> {
}
