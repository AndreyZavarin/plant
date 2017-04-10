package com.demo.services.impls

import com.demo.dto.TariffDto
import com.demo.exceptions.NotFoundException
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
        val tariff = Tariff(dto.name, dto.type, dto.cost, dto.quantity, dto.description, dto.lifetimeInMillis)
        return tariffRepo.save(tariff)
    }

    override fun read(id: Long): Optional<Tariff> {
        return tariffRepo.findOne(id)
    }

    override fun update(id: Long, dto: TariffDto): Tariff {
        val tariff = tariffRepo.findOne(id).orElseThrow(NotFoundException.sup)

        tariff.name = dto.name
        tariff.lifetimeInMillis = dto.lifetimeInMillis
        tariff.description = dto.description
        tariff.type = dto.type
        tariff.cost = dto.cost
        tariff.quantity = dto.quantity

        return tariffRepo.save(tariff)
    }

    override fun delete(id: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}