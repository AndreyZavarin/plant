package com.demo.services.impls

import com.demo.dto.ClientDto
import com.demo.models.Client
import com.demo.repositories.ClientRepository
import com.demo.services.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientServiceImpl
@Autowired constructor(val clientRepository: ClientRepository) : ClientService {

    override fun create(dto: ClientDto): Client {
        val client = Client(dto.firstName, dto.lastName, dto.middleName, dto.gender, dto.birthDate)
        return clientRepository.save(client)
    }

    override fun read(id: Long): Optional<Client> {
        return clientRepository.findOne(id)
    }

    override fun update(id: Long, dto: ClientDto): Client {
        val client = clientRepository.findOne(id).orElseThrow(com.demo.exceptions.NotFoundException.notFoundExceptionSupplier)

        client.firstName = dto.firstName
        client.middleName = dto.middleName
        client.lastName = dto.lastName

        client.gender = dto.gender
        client.birthDate = dto.birthDate

        return clientRepository.save(client)
    }

    override fun getClientPage(pageable: Pageable): Page<ClientDto> {
        val findAll = clientRepository.findAll<Sort>(pageable)
        return findAll.map(::ClientDto)
    }

    override fun delete(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}