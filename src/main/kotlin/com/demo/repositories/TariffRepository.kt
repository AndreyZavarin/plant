package com.demo.repositories

import com.demo.models.Tariff
import org.springframework.data.jpa.repository.JpaRepository

interface TariffRepository : JpaRepository<Tariff, Long>