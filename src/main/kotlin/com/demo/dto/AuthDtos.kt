package com.demo.dto

import java.io.Serializable

data class AuthRequest(
        val password: String,
        val username: String
) : Serializable

data class AuthResponse(
        val token: String
) : Serializable