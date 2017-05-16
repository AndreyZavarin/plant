package com.demo.services.impls

import com.demo.dto.SubscriptionDto
import com.demo.exceptions.NotFoundException.notFoundExceptionSupplier
import com.demo.models.Subscription
import com.demo.repositories.ClientRepository
import com.demo.repositories.SubscriptionRepository
import com.demo.repositories.TariffRepository
import com.demo.services.SubscriptionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SubscriptionServiceImpl
@Autowired constructor(
        val subscriptionRepository: SubscriptionRepository,
        val clientRepository: ClientRepository,
        val tariffRepository: TariffRepository) : SubscriptionService {

    override fun create(dto: SubscriptionDto): Subscription {
        val client = clientRepository.findById(dto.clientId).orElseThrow(notFoundExceptionSupplier)
        val tariff = tariffRepository.findById(dto.tariffId).orElseThrow(notFoundExceptionSupplier)

        val subscription = Subscription(client, tariff)

        return subscriptionRepository.save(subscription)
    }

    override fun read(id: Long): Optional<Subscription> {
        return subscriptionRepository.findById(id)
    }

    override fun update(id: Long, dto: SubscriptionDto): Subscription {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun decrement(id: Long): Subscription {
        val subscription = subscriptionRepository.findById(id).orElseThrow(notFoundExceptionSupplier)

        subscription.quantity--

        return subscriptionRepository.save(subscription)
    }
}