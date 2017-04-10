package com.demo.models

import com.demo.models.enums.Role
import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "app_user")
class AppUser() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    var id: Long = -1

    @Column(name = "login", nullable = false, unique = true, updatable = false)
    var login: String = ""

    @Column(name = "password_hash", nullable = false)
    @JsonIgnore
    var passwordHash: String = ""

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    var role: Role = Role.NONE

    @Column(name = "password_date")
    var passwordSetDate: LocalDateTime = LocalDateTime.now()

    constructor(login: String, passwordHash: String, role: Role) : this() {
        this.login = login
        this.passwordHash = passwordHash
        this.role = role
    }
}