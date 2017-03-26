package com.demo.models

import com.demo.models.enums.SubState
import java.math.BigInteger
import java.time.LocalDateTime
import javax.persistence.*

class Subscription() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long = -1

    @Column
    @Enumerated(EnumType.ORDINAL)
    var state = SubState.IDLE

    @Column
    var number: Int = -1

    @Column
    var name: String = ""

    @Column
    var startDate: LocalDateTime = LocalDateTime.now()

    @Column
    var endDate: LocalDateTime = LocalDateTime.now()

    @Column
    var balance: BigInteger = BigInteger.ZERO

    @Column
    var description: String = ""

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Client::class)
    lateinit var client: Client

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Tariff::class)
    lateinit var tariff: Tariff

}