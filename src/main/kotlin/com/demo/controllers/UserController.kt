package com.demo.controllers

import com.demo.controllers.validators.AppUserDtoValidator
import com.demo.models.AppUser
import com.demo.services.AppUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.annotation.Secured
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*

@RestController
//@PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
open class UserController @Autowired constructor(private val userService: AppUserService, private val userValidator: AppUserDtoValidator) {

    @InitBinder()
    private fun initBinder(binder: WebDataBinder) {
        binder.addValidators(userValidator)
    }

    @RequestMapping(value = "/user/{id}", method = arrayOf(RequestMethod.GET), produces = arrayOf("application/json; charset=utf-8"))
    fun get(@PathVariable id: Long): AppUser {
        return userService.read(id);
    }

}
