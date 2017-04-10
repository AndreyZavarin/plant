package com.demo.dto

import com.demo.models.Tariff

data class TariffDto(
        val id: Long
) {
    constructor(tariff: Tariff) : this(tariff.id)
}