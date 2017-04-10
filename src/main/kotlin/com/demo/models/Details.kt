package com.demo.models

import javax.persistence.*

@Entity
@Table(name = "client_details")
class Details() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    var id = -1

    @Column(length = DESCRIPTION_SIZE)
    var experience: String = ""

    @Column(length = DESCRIPTION_SIZE)
    var achievements: String = ""

    @Column(length = DESCRIPTION_SIZE)
    var targets: String = ""

    @Column(length = DESCRIPTION_SIZE)
    var lastCold: String = ""

    @Column(length = DESCRIPTION_SIZE)
    var contraindications: String = ""

    @Column(length = DESCRIPTION_SIZE)
    var injuries: String = ""

    @Column(length = DESCRIPTION_SIZE)
    var referrer: String = ""

    @Column()
    var crossFit: Boolean = false

    @Column(name = "rules_accepted")
    var rulesAccepted: Boolean = false

    companion object {
        const private val DESCRIPTION_SIZE = 500
    }
}
