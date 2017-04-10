package com.demo.repositories

import com.demo.models.Subscription
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubscriptionRepository : JpaRepository<Subscription, Long>