package com.demo.services.impls

import com.demo.dto.ClientDto
import com.demo.models.Client
import com.demo.repositories.ClientRepository
import com.demo.services.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientServiceImpl
@Autowired constructor(val clientRepository: ClientRepository) : ClientService {

    override fun create(dto: ClientDto): Client {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun read(id: Long): Optional<Client> {
        return clientRepository.findOne(id)
    }

    override fun update(dto: ClientDto): Client {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}