package com.demo.dto

import java.io.Serializable

data class AuthRequest(
        val username: String,
        val password: String
) : Serializable

data class AuthResponse(
        val token: String
) : Serializable

data class StringResponse(
        val response: String,
        val state: String
) : Serializable