package com.demo.controllers

import com.demo.dto.StringResponse
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
class MainController {

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    fun getHomePage(principal: Principal?): StringResponse {
        return StringResponse("Hello, ${principal?.name?: "guest"}", "ok");
    }

}