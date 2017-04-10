package com.demo.services.impls

import com.demo.dto.ClientDto
import com.demo.models.Client
import com.demo.repositories.ClientRepository
import com.demo.services.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientServiceImpl
@Autowired constructor(clientRepository: ClientRepository) : ClientService {

    override fun create(dto: ClientDto): Client {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun read(id: Long): Client {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(dto: ClientDto): Client {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}