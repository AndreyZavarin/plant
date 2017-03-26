package com.demo.models

import com.demo.models.enums.TariffType
import java.math.BigDecimal
import javax.persistence.*

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

}