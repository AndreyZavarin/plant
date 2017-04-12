package com.demo.services

import com.demo.dto.ClientDto
import com.demo.models.Client
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ClientService : CrudService<Client, ClientDto, Long> {
    fun getClientPage(pageable: Pageable): Page<ClientDto>
}