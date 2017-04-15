package com.demo.dto

import com.demo.models.Subscription
import com.demo.models.enums.SubState

data class SubscriptionDto(
        val id: Long,
        val subState: SubState,
        val quantity: Int,
        val clientId: Long,
        val tariffId: Long) {

    constructor(subscription: Subscription)
            : this(subscription.id, subscription.state, subscription.quantity, subscription.client.id, subscription.tariff.id)

}