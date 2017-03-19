package com.demo.services

import com.demo.dto.AppUserDto
import com.demo.models.AppUser

interface AppUserService : CrudService<AppUser, AppUserDto, Long> {
    fun findUserByLogin(login: String): AppUser
}
