package com.demo.utility

import com.demo.models.AppUser
import com.demo.services.auth.CurrentUser
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun Date.toLocalDateTime(): LocalDateTime = this.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

fun AppUser.getUserDetails(): UserDetails = CurrentUser(this)