package com.demo.controllers

import com.demo.services.AppUserService
import org.springframework.beans.factory.annotation.Autowired

class UserController @Autowired constructor(val userService: AppUserService) {



}
