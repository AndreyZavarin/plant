package com.demo.services

import java.util.*

interface CrudService<Entity, in Dto, in Key> {
    fun create(dto: Dto): Entity
    fun read(id: Key): Optional<Entity>
    fun update(dto: Dto): Entity
    fun delete(id: Key)
}