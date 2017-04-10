package com.demo.services.impls

import com.demo.dto.TariffDto
import com.demo.models.Tariff
import com.demo.repositories.TariffRepository
import com.demo.services.TariffService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TariffServiceImpl
@Autowired constructor(val tariffRepo: TariffRepository) : TariffService {

    override fun create(dto: TariffDto): Tariff {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun read(id: Long): Optional<Tariff> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(id: Long, dto: TariffDto): Tariff {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}