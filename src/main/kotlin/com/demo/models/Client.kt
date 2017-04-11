package com.demo.models

import com.demo.models.enums.Gender
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.ArrayList
import javax.persistence.*

@Entity
class Client() {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long = -1

    @Column(name = "first_name", nullable = false)
    var firstName: String = ""

    @Column(name = "last_name", nullable = false)
    var lastName: String = ""

    @Column(name = "middle_name")
    var middleName: String = ""

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    var gender: Gender = Gender.MALE

    @Column(name = "birth_date", nullable = false)
    var birthDate: LocalDate = LocalDate.now()

    @Column(name = "date_created")
    var dateCreated = LocalDateTime.now()

    @OneToMany(fetch = FetchType.LAZY)
    var contacts: List<Contact> = ArrayList()

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    var subscriptions: List<Subscription> = ArrayList()

    @ManyToOne(targetEntity = Details::class, optional = true, fetch = FetchType.LAZY)
    //@OneToOne is not very flexible, but in fact, that is one to one mapping
    lateinit var details: Details

    constructor(firstName: String, lastName: String, middleName: String, gender: Gender, birthDate: LocalDate) : this() {
        this.firstName = firstName
        this.lastName = lastName
        this.middleName = middleName
        this.gender = gender
        this.birthDate = birthDate
    }
}