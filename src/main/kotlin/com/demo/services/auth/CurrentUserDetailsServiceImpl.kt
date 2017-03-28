package com.demo.services.auth

import com.demo.models.AppUser
import com.demo.services.AppUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
open class CurrentUserDetailsServiceImpl
@Autowired constructor(private val appUserService: AppUserService) : UserDetailsService {

    override fun loadUserByUsername(login: String): UserDetails {
        return appUserService.findUserByLogin(login).getUserDetails()
    }

}

class CurrentUser(val appUser: AppUser) : User(appUser.login, appUser.passwordHash, appUser.role.getAuthorities())

fun AppUser.getUserDetails(): UserDetails = CurrentUser(this)