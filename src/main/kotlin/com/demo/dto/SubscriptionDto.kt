package com.demo.dto

import com.demo.models.Subscription
import com.demo.models.enums.SubState

data class SubscriptionDto(
        val id: Long,
        val subState: SubState,
        val quantity: Int,
        val clientDto: ClientDto,
        val tariffDto: TariffDto) {

    constructor(subscription: Subscription) : this(subscription.id, subscription.state, subscription.quantity,
            ClientDto(subscription.client), TariffDto(subscription.tariff))

}