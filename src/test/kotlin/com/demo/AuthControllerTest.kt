package com.demo

import com.demo.configs.TokenUtils
import com.demo.dto.AuthRequest
import com.demo.models.AppUser
import com.demo.repositories.AppUserRepository
import com.demo.services.auth.CurrentUser
import com.demo.services.auth.getUserDetails
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.headers.HeaderDocumentation.headerWithName
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.snippet.Snippet
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.temporal.ChronoUnit
import java.util.*

class AuthControllerTest : AbstractIntegrationTest() {

    @Autowired
    lateinit var appUserRepository: AppUserRepository

    @Autowired
    lateinit var tokenUtils: TokenUtils

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun testUserRepo() {
        val findAll = appUserRepository.findAll();
        Assert.assertThat(findAll.size, Matchers.`is`(2))
    }

    @Test
    fun authAsAdminAndGetValidToken() {
        val jsonBody = objectMapper.writeValueAsString(AuthRequest("admin", "password"));
        val request = MockMvcRequestBuilders.post("/auth").content(jsonBody).contentType(jsonType)

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(jsonType))
                .andDo { validateToken(it.response.contentAsString.getTokenFromJson(), "admin") }
                .andDo(document("api/auth", *getAuthMethodSnippets()))
    }

    private fun getAuthMethodSnippets(): Array<Snippet> {
        val requestFields = PayloadDocumentation.requestFields(
                PayloadDocumentation.fieldWithPath("username").description("User's login"),
                PayloadDocumentation.fieldWithPath("password").description("User's password")
        )

        val responseFields = PayloadDocumentation.responseFields(
                PayloadDocumentation.fieldWithPath("token").description("Json Web Token, used for authorization")
        )

        return arrayOf(requestFields, responseFields);
    }

    @Test
    fun refreshToken() {
        val (_, initialToken) = getUserAndHisToken("admin")

        Thread.sleep(500)

        val request = MockMvcRequestBuilders.get("/refresh").header(tokenUtils.tokenHeader, initialToken)
        mockMvc.perform(request)
                .andDo(checkValidnessOfTokenAndItsExpirationTime(initialToken))
                .andDo(document("api/refresh", *getRefreshMethodSnippets()))
    }

    private fun checkValidnessOfTokenAndItsExpirationTime(initialToken: String): (MvcResult) -> Unit {
        return {
            val refreshedToken = it.response.contentAsString.getTokenFromJson()
            validateToken(refreshedToken, "admin")

            val expirationDateFromToken = tokenUtils.getExpirationDateFromToken(refreshedToken)
            val tokenCreationDate = tokenUtils.getTokenCreationDate(refreshedToken)

            Assert.assertTrue(tokenCreationDate!!.isAfter(tokenUtils.getTokenCreationDate(initialToken)))
            Assert.assertTrue(expirationDateFromToken!!.isAfter(tokenUtils.getTokenCreationDate(initialToken)))

            val between = ChronoUnit.SECONDS.between(tokenCreationDate, expirationDateFromToken)
            Assert.assertEquals(tokenUtils.expiration, between)
        }
    }

    private fun getRefreshMethodSnippets(): Array<Snippet> {
        val headerDescriptor = headerWithName(tokenUtils.tokenHeader).description("User's JWT");
        val requestHeaders = HeaderDocumentation.requestHeaders(headerDescriptor)

        val responseFields = PayloadDocumentation.responseFields(
                PayloadDocumentation.fieldWithPath("token").description("Refreshed user's JWT")
        )

        return arrayOf(requestHeaders, responseFields)
    }

    @Test
    fun accessProtectedResource() {
        val (admin, adminsToken) = getUserAndHisToken("admin")
        val adminRequest = MockMvcRequestBuilders.get("/user/${admin.id}").header(tokenUtils.tokenHeader, adminsToken)

        mockMvc.perform(adminRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(jsonType))

        val (user, usersToken) = getUserAndHisToken("user")
        val userRequest = MockMvcRequestBuilders.get("/user/${user.id}").header(tokenUtils.tokenHeader, usersToken)

        mockMvc.perform(userRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is4xxClientError)
                .andExpect(MockMvcResultMatchers.status().reason("Access is denied"))
    }

    private fun getUserAndHisToken(login: String): Pair<AppUser, String> {
        val user = appUserRepository.findUserByLogin(login)
        return user to generateValidToken(user.getUserDetails())
    }

    private fun generateValidToken(userDetails: UserDetails): String {
        val generatedToken = tokenUtils.generateToken(userDetails)
        Assert.assertTrue(tokenUtils.tokenIsValid(generatedToken, userDetails))
        return generatedToken
    }

    private fun validateToken(token: String, login: String) {
        val appUser = appUserRepository.findUserByLogin(login)
        Assert.assertTrue(tokenUtils.tokenIsValid(token, CurrentUser(appUser)))
    }

    private fun String.getTokenFromJson(): String {
        val typeRef = object : TypeReference<HashMap<String, String>>() {}
        val readValue = objectMapper.readValue<Map<String, String>>(this, typeRef)
        return readValue.get("token")!!
    }

}
