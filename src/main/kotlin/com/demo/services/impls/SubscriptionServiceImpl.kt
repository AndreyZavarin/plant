package com.demo.services.impls

import com.demo.dto.SubscriptionDto
import com.demo.models.Subscription
import com.demo.services.SubscriptionService
import org.springframework.stereotype.Service
import java.util.*

@Service
class SubscriptionServiceImpl : SubscriptionService {

    override fun create(dto: SubscriptionDto): Subscription {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun read(id: Long): Optional<Subscription> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(id: Long, dto: SubscriptionDto): Subscription {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}