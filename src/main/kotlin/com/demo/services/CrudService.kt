package com.demo.services

interface CrudService<out Entity, in Dto, in Key> {
    fun create(dto: Dto): Entity
    fun read(id: Key): Entity
    fun update(dto: Dto): Entity
    fun delete(id: Key)
}