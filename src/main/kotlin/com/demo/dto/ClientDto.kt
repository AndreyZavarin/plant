package com.demo.dto

import com.demo.models.Client

data class ClientDto(
        var id: Long
) {
    constructor(client: Client) : this(client.id)
}