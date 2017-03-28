package com.demo.controllers

import com.demo.configs.TokenUtils
import com.demo.dto.AuthRequest
import com.demo.dto.AuthResponse
import com.demo.services.auth.CurrentUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@RestController
class AuthController
@Autowired constructor(private val authenticationManager: AuthenticationManager,
                       private val tokenUtils: TokenUtils,
                       private val userDetailsService: UserDetailsService) {

    @RequestMapping(value = "auth", method = arrayOf(RequestMethod.POST))
    @Throws(AuthenticationException::class)
    fun authenticationRequest(@RequestBody(required = true) authenticationRequest: AuthRequest): ResponseEntity<*> {
        // Perform the authentication
        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password)
        val authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken)
        SecurityContextHolder.getContext().authentication = authentication

        // Reload password post-authentication so we can generate token
        val userDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)
        val token = tokenUtils.generateToken(userDetails)

        // Return the token
        return ResponseEntity.ok(AuthResponse(token))
    }

    @RequestMapping(value = "/refresh", method = arrayOf(RequestMethod.GET))
    fun authenticationRequest(request: HttpServletRequest): ResponseEntity<*> {
        val token = request.getHeader(tokenUtils.tokenHeader)
        val username = tokenUtils.getUsernameFromToken(token)

        val user = userDetailsService.loadUserByUsername(username) as CurrentUser
        if (tokenUtils.canTokenBeRefreshed(token, user.appUser.passwordSetDate)) {
            val refreshedToken = tokenUtils.refreshToken(token)
            if (refreshedToken != null) {
                return ResponseEntity.ok(AuthResponse(refreshedToken))
            }
        }

        return ResponseEntity.badRequest().body<Any>(null)
    }

}