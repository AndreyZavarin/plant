package com.demo.dto

import com.demo.models.enums.Role

data class AppUserDto(
        var login: String,
        var password: String,
        var passwordRepeated: String,
        var role: Role,
        var id: Long?
)