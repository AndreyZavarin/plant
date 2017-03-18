package com.demo.services

interface CrudService<out E, in D, in L> {
    fun create(dto: D): E
    fun read(id: L): E
    fun update(dto: D): E
    fun delete(id: L)
}