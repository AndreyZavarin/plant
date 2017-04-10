package com.demo.dto

import com.demo.models.Tariff
import com.demo.models.enums.TariffType
import java.math.BigDecimal

data class TariffDto(
        val id: Long,
        val name: String,
        val cost: BigDecimal,
        val description: String,
        val type: TariffType,
        val quantity: Int,
        val lifetimeInMillis: Long
) {
    constructor(tariff: Tariff) : this(tariff.id, tariff.name, tariff.cost, tariff.description, tariff.type, tariff.quantity, tariff.lifetimeInMillis)
}