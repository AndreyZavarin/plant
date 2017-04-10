package com.demo

import com.demo.models.Subscription
import com.demo.repositories.ClientRepository
import com.demo.repositories.SubscriptionRepository
import com.demo.repositories.TariffRepository
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired


class SubscriptionTest() : AbstractIntegrationTest() {

    @Autowired
    lateinit var clientRepo: ClientRepository

    @Autowired
    lateinit var tariffRepo: TariffRepository

    @Autowired
    lateinit var subscriptionRepo: SubscriptionRepository

    @Test
    fun test_1() {
        val client = clientRepo.findFirstByOrderByIdAsc()
        val tariff = tariffRepo.findFirstByOrderByIdAsc()

        val subscription = Subscription(client, tariff)
        subscriptionRepo.save(subscription)
        println("subscription = ${subscription}")
    }

}