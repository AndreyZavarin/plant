package com.demo.services

import com.demo.dto.TariffDto
import com.demo.models.Tariff

interface TariffService : CrudService<Tariff, TariffDto, Long>