package com.demo.controllers

import com.demo.dto.ClientDto
import com.demo.exceptions.NotFoundException
import com.demo.services.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@PreAuthorize("hasRole('ADMIN')")
class ClientController @Autowired constructor(
        val clientService: ClientService
) {

    @RequestMapping(value = "/client/", method = arrayOf(RequestMethod.POST), produces = arrayOf("application/json; charset=utf-8"))
    fun create(@RequestBody dto: ClientDto): ClientDto {
        val client = clientService.create(dto)
        return ClientDto(client)
    }

    @RequestMapping(value = "/client/{id}", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json; charset=utf-8"))
    fun read(@PathVariable id: Long): ClientDto {
        val client = clientService.read(id)
        return ClientDto(client.orElseThrow(NotFoundException.sup))
    }

    @RequestMapping(value = "/client/{id}", method = arrayOf(RequestMethod.POST), produces = arrayOf("application/json; charset=utf-8"))
    fun update(@PathVariable id: Long, clientDto: ClientDto): ClientDto {
        val client = clientService.update(id, clientDto)
        return ClientDto(client)
    }

}