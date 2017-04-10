package com.demo.models

import com.demo.models.enums.TariffType
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.temporal.ChronoField
import javax.persistence.*

@Entity
class Tariff() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long = -1

    @Column(name = "name")
    var name: String = ""

    @Column
    @Enumerated(EnumType.ORDINAL)
    var type: TariffType = TariffType.EXPRESS

    @Column
    var cost: BigDecimal = BigDecimal.ZERO

    @Column
    var quantity: Int = 0

    @Column
    var description: String = ""

    @Column(name = "lifetime")
    var lifetimeInMillis: Long = 0

    constructor(name: String, type: TariffType, cost: BigDecimal, quantity: Int, description: String, lifetimeInMillis: Long) : this() {
        this.name = name
        this.type = type
        this.cost = cost
        this.quantity = quantity
        this.description = description
        this.lifetimeInMillis = lifetimeInMillis
    }

    fun getStartToEndTimeFromNow(): Pair<LocalDateTime, LocalDateTime> {
        val start = LocalDateTime.now()
        val end = start.plus(lifetimeInMillis, ChronoField.MILLI_OF_DAY.baseUnit)

        return start to end
    }

}