package com.demo.controllers

import com.demo.dto.ClientDto
import com.demo.dto.TariffDto
import com.demo.exceptions.NotFoundException
import com.demo.services.TariffService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = "/tariff")
@PreAuthorize("hasRole('ADMIN')")
class TariffController @Autowired constructor(val tariffService: TariffService) {

    @RequestMapping(method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun create(@RequestBody(required = true) dto: TariffDto): ResponseEntity<TariffDto> {
        val tariff = tariffService.create(dto)
        return ResponseEntity.ok(TariffDto(tariff))
    }

    @RequestMapping(value = "/{id}",
            method = arrayOf(RequestMethod.GET),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun read(@PathVariable id: Long): ResponseEntity<TariffDto> {
        val optional = tariffService.read(id)
        return ResponseEntity.ok(TariffDto(optional.orElseThrow(NotFoundException)))
    }

    @RequestMapping(value = "/{id}",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun update(@PathVariable id: Long, @RequestBody(required = true) dto: TariffDto): ResponseEntity<TariffDto> {
        val tariff = tariffService.update(id, dto)
        return ResponseEntity.ok(TariffDto(tariff));
    }

}

