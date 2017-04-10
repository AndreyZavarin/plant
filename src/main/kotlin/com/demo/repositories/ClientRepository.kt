package com.demo.repositories

import com.demo.models.Client
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ClientRepository : JpaRepository<Client, Long> {

    fun findFirstByOrderByIdAsc(): Client

}