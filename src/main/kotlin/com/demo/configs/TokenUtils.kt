package com.demo.configs

import com.demo.services.auth.CurrentUser
import com.demo.utility.toLocalDateTime
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
open class TokenUtils {

    @Value("\${demo.token.secret}")
    private val secret: String? = null

    @Value("\${demo.token.expiration}")
    private val expiration: Long? = null

    fun getUsernameFromToken(token: String): String? {
        var username: String?
        try {
            val claims = getClaimsFromToken(token)
            username = claims?.subject
        } catch (e: Exception) {
            username = null
        }
        return username
    }

    fun getTokenCreationDate(token: String): LocalDateTime? {
        var created: Date?
        try {
            val claims = getClaimsFromToken(token)
            created = Date(claims!!["created"] as Long)
        } catch (e: Exception) {
            created = null
        }

        return created?.toLocalDateTime()
    }

    fun getExpirationDateFromToken(token: String): LocalDateTime? {
        var expiration: Date?
        try {
            val claims = getClaimsFromToken(token)
            expiration = claims?.expiration
        } catch (e: Exception) {
            expiration = null
        }
        return expiration?.toLocalDateTime()
    }

    private fun getClaimsFromToken(token: String): Claims? {
        var claims: Claims?
        try {
            claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).body
        } catch (e: Exception) {
            claims = null
        }
        return claims
    }

    private fun generateCurrentDate(): Date {
        return Date(System.currentTimeMillis())
    }

    private fun generateExpirationDate(): Date {
        return Date(System.currentTimeMillis() + expiration!! * 1000)
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration == null || expiration.isBefore(LocalDateTime.now())
    }

    private fun isCreatedBeforeLastPasswordReset(created: LocalDateTime, lastPasswordReset: LocalDateTime?): Boolean {
        return lastPasswordReset != null && created.isBefore(lastPasswordReset)
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        claims.put("sub", userDetails.username)
        claims.put("created", generateCurrentDate())
        return generateToken(claims)
    }

    private fun generateToken(claims: Map<String, Any>?): String {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact()
    }

    fun canTokenBeRefreshed(token: String, lastPasswordReset: LocalDateTime): Boolean? {
        val date = getTokenCreationDate(token)
        return date != null && isCreatedBeforeLastPasswordReset(date, lastPasswordReset) && !isTokenExpired(token)
    }

    fun refreshToken(token: String): String? {
        var refreshedToken: String?
        try {
            val claims = getClaimsFromToken(token)
            claims?.put("created", generateCurrentDate())
            refreshedToken = generateToken(claims)
        } catch (e: Exception) {
            refreshedToken = null
        }

        return refreshedToken
    }

    fun tokenIsValid(token: String, userDetails: UserDetails): Boolean {
        val user = userDetails as CurrentUser
        val username = getUsernameFromToken(token)

        val created = getTokenCreationDate(token)

        val isCreatedBeforeLastPasswordReset = created == null || isCreatedBeforeLastPasswordReset(created, user.appUser.passwordSetDate);
        return username == user.username && !isTokenExpired(token) && isCreatedBeforeLastPasswordReset
    }

}
