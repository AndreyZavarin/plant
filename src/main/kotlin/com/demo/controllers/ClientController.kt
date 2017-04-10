package com.demo.controllers

import com.demo.dto.ClientDto
import com.demo.exceptions.NotFoundException
import com.demo.services.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/client")
@PreAuthorize("hasRole('ADMIN')")
class ClientController @Autowired constructor(val clientService: ClientService) {

    @RequestMapping(method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun create(@RequestBody dto: ClientDto): ResponseEntity<ClientDto> {
        val client = clientService.create(dto)
        return ResponseEntity.ok(ClientDto(client))
    }

    @RequestMapping(value = "/{id}",
            method = arrayOf(RequestMethod.GET),
            produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
    fun read(@PathVariable id: Long): ResponseEntity<ClientDto> {
        val client = clientService.read(id)
        return ResponseEntity.ok(ClientDto(client.orElseThrow(NotFoundException.sup)))
    }

    @RequestMapping(value = "/{id}",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
            produces = arrayOf("application/json; charset=utf-8"))
    fun update(@PathVariable id: Long, @RequestBody(required = true) dto: ClientDto): ResponseEntity<ClientDto> {
        val client = clientService.update(id, dto)
        return ResponseEntity.ok(ClientDto(client))
    }

}