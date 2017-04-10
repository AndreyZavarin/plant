package com.demo.dto

import com.demo.models.Client
import com.demo.models.enums.Gender
import java.time.LocalDate

data class ClientDto(
        var id: Long,
        val firstName: String,
        val lastName: String,
        val middleName: String,
        val gender: Gender,
        val birthDate: LocalDate
) {
    constructor(client: Client) : this(client.id, client.firstName, client.lastName, client.middleName, client.gender, client.birthDate)
}