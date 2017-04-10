package com.demo.services

import com.demo.dto.ClientDto
import com.demo.models.Client

interface ClientService : CrudService<Client, ClientDto, Long>