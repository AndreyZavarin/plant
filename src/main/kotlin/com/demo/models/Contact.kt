package com.demo.models

import com.demo.models.enums.ContactType
import javax.persistence.*


@Entity
class Contact() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long = -1;

    @Column
    var type: ContactType = ContactType.EMAIL

    @Column(nullable = false)
    var identifier: String = ""

    constructor(type: ContactType, identifier: String) : this() {
        this.type = type
        this.identifier = identifier
    }

}