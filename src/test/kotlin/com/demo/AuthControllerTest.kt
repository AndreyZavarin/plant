package com.demo

import com.demo.configs.TokenUtils
import com.demo.dto.AuthRequest
import com.demo.repositories.AppUserRepository
import com.demo.services.auth.CurrentUser
import com.demo.utility.getUserDetails
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.snippet.Snippet
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.util.*

class AuthControllerTest : IntegrationTest() {

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

        val document = MockMvcRestDocumentation.document("api/auth", *getAuthMethodSnippets())

        mockMvc.perform(request)
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(jsonType))
                .andDo { validateToken(it.response.contentAsString, "admin") }
                .andDo(document)
    }

    @Test
    fun refreshToken() {
        val admin = appUserRepository.findUserByLogin("admin")
        val authToken = generateValidToken(admin.getUserDetails())

        val request = MockMvcRequestBuilders.get("/refresh").header(tokenUtils.tokenHeader, authToken)

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
    }

    private fun generateValidToken(userDetails: UserDetails): String {
        val generatedToken = tokenUtils.generateToken(userDetails)
        Assert.assertTrue(tokenUtils.tokenIsValid(generatedToken, userDetails))
        return generatedToken
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

    private fun validateToken(token: String, login: String) {
        val typeRef = object : TypeReference<HashMap<String, String>>() {}
        val readValue = objectMapper.readValue<Map<String, String>>(token, typeRef)

        val appUser = appUserRepository.findUserByLogin(login)
        Assert.assertTrue(tokenUtils.tokenIsValid(readValue.get("token")!!, CurrentUser(appUser)))
    }

}
