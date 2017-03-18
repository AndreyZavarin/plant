package com.demo.dto

import com.demo.models.enums.Role
import org.hibernate.validator.constraints.NotEmpty
import org.jetbrains.annotations.Nullable
import javax.validation.constraints.NotNull

data class AppUserDto(
        @NotEmpty var login: String,
        @NotEmpty var password: String,
        @NotEmpty var passwordRepeated: String,
        @NotNull var role: Role,
        @Nullable var id: Long?
)