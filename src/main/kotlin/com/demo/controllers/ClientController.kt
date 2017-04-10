package com.demo.controllers

import com.demo.dto.ClientDto
import com.demo.exceptions.NotFoundException
import com.demo.services.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@PreAuthorize("hasRole('ADMIN')")
class ClientController @Autowired constructor(
        val clientService: ClientService
) {

    @RequestMapping(value = "/client/{id}", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json; charset=utf-8"))
    fun get(@PathVariable id: Long): ClientDto {
        val client = clientService.read(id)
        return ClientDto(client.orElseThrow(NotFoundException.sup))
    }

}