package com.demo.controllers

import com.demo.dto.SubscriptionDto
import com.demo.services.SubscriptionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/subscription")
@PreAuthorize("hasRole('ADMIN')")
class SubscriptionController
@Autowired constructor(val subscriptionService: SubscriptionService) {

    @RequestMapping(value = "{id}/",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
            produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE))
    fun update(@PathVariable id: Long, @RequestBody(required = true) dto: SubscriptionDto): ResponseEntity<SubscriptionDto> {
        val updatedSubscription = subscriptionService.update(id, dto)
        return ResponseEntity.ok(SubscriptionDto(updatedSubscription))
    }

}
