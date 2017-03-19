package com.demo.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class AuthenticationTokenFilter : UsernamePasswordAuthenticationFilter() {

    @Autowired
    lateinit var tokenUtils: TokenUtils

    @Autowired
    lateinit var userDetailsService: UserDetailsService

    @Value("\${demo.token.header}")
    var tokenHeader: String = ""

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val authToken: String? = httpRequest.getHeader(tokenHeader)

        println("request.cookies = ${request.cookies}")
        
        val username = tokenUtils.getUsernameFromToken(authToken)

        if (username != null && authToken != null && SecurityContextHolder.getContext().authentication == null) {
            val userDetails = this.userDetailsService.loadUserByUsername(username)
            if (tokenUtils.tokenIsValid(authToken, userDetails)) {
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(httpRequest)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        chain.doFilter(request, response)
    }

}
