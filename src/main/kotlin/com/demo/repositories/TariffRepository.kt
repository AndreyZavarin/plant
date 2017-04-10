package com.demo.repositories

import com.demo.models.Tariff
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TariffRepository : JpaRepository<Tariff, Long>