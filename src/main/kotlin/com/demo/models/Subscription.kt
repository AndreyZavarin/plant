package com.demo.models

import com.demo.models.enums.SubState
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Subscription() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long = 1

    @Column
    @Enumerated(EnumType.ORDINAL)
    var state = SubState.IDLE

    @Column
    var startDate: LocalDateTime = LocalDateTime.now()

    @Column
    var endDate: LocalDateTime = LocalDateTime.now()

    @Column
    var quantity: Int = 0

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Client::class)
    lateinit var client: Client

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Tariff::class)
    lateinit var tariff: Tariff

    constructor(client: Client, tariff: Tariff) : this() {
        this.client = client
        this.tariff = tariff

        val (startDate, endDate) = tariff.getStartToEndTimeFromNow()
        this.startDate = startDate
        this.endDate = endDate
        this.quantity = tariff.quantity
    }

    override fun toString(): String {
        return "Subscription(id=$id, state=$state, startDate=$startDate, endDate=$endDate, quantity=$quantity, client=$client, tariff=$tariff)"
    }


}