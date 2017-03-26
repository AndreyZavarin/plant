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
import javax.servlet.http.Cookie

@Component
open class TokenUtils {

    @Value("\${demo.token.secret}")
    private val secret: String = ""

    @Value("\${demo.token.expiration}")
    private val expiration: Long = 0

    @Value("\${demo.token.header}")
    var tokenHeader: String = ""

    @Value("\${demo.token.cookie.name}")
    var cookieName: String = ""

    /**
     * try to parse jwt token claims and return subject from it
     * @param token - token that we want to parse
     * @return - subject (user login) or null
     */
    fun getUsernameFromToken(token: String?): String? = token?.getClaimsFromToken()?.subject

    /**
     * try to parse jwt token and return date of token's creation
     * @param token - token that we want to parse
     * @return - issuedAt (timestamp of token's creation) or null
     */
    fun getTokenCreationDate(token: String): LocalDateTime? = token.getClaimsFromToken()?.issuedAt?.toLocalDateTime()

    /**
     * try to parse jwt token and return date of it's expiration time
     * @param token - token that we want to parse
     * @return- token expiration date or null
     */
    fun getExpirationDateFromToken(token: String): LocalDateTime? = token.getClaimsFromToken()?.expiration?.toLocalDateTime()

    private fun generateCurrentDate(): Date = Date(System.currentTimeMillis())

    private fun generateExpirationDate(): Date = Date(System.currentTimeMillis() + expiration * 1000)

    private fun isCreatedAfterLastPasswordReset(created: LocalDateTime, lastPasswordReset: LocalDateTime): Boolean = created.isAfter(lastPasswordReset)

    /**
     * try to find cookie with token in cookies array
     * @param cookies - array of cookies where search at
     */
    fun findTokenInCookie(cookies: Array<Cookie>?): String? {
        if (cookies != null) {
            cookies
                    .filter { it.name == cookieName }
                    .forEach { return it.value }
        }
        return null
    }

    private fun tokenIsExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token) ?: return false
        return expiration.isBefore(LocalDateTime.now())
    }

    /**
     * generates jwt token from user details
     * @param - user details
     * @return - json web token
     */
    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        claims.put("sub", userDetails.username)
        claims.put("role", userDetails.authorities)
        return generateToken(claims)
    }

    private fun generateToken(claims: Map<String, Any>?): String {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(generateCurrentDate())
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact()
    }

    fun canTokenBeRefreshed(token: String, lastPasswordReset: LocalDateTime): Boolean {
        val date = getTokenCreationDate(token) ?: return false;
        return isCreatedAfterLastPasswordReset(date, lastPasswordReset) && !tokenIsExpired(token)
    }

    fun refreshToken(token: String): String? {
        val claimsFromToken = token.getClaimsFromToken() ?: return null;
        claimsFromToken.issuedAt = generateCurrentDate()

        return generateToken(claimsFromToken)
    }

    fun tokenIsValid(token: String, userDetails: UserDetails): Boolean {
        val user = userDetails as CurrentUser
        val username = getUsernameFromToken(token)

        val created = getTokenCreationDate(token)

        val isCreatedAfterLastPasswordReset = created != null && isCreatedAfterLastPasswordReset(created, user.appUser.passwordSetDate);
        return username == user.username && !tokenIsExpired(token) && isCreatedAfterLastPasswordReset
    }

    private fun String.getClaimsFromToken(): Claims? {
        var claims: Claims?
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(this).body
        } catch (e: Exception) {
            //e.printStackTrace()
            claims = null
        }
        return claims
    }

}
