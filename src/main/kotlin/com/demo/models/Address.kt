package com.demo.models

import javax.persistence.*

@Entity
class Address() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long = 1

    @Column(nullable = false)
    var address: String = ""

    constructor(address: String) : this() {
        this.address = address
    }

}