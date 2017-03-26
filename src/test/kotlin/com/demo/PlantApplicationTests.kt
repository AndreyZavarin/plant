package com.demo

import com.demo.configs.TokenUtils
import com.demo.dto.AuthRequest
import com.demo.repositories.AppUserRepository
import com.demo.services.auth.CurrentUser
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers.`is`
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext
import java.nio.charset.Charset
import java.util.HashMap

@RunWith(SpringRunner::class)
@SpringBootTest(properties = arrayOf("application-test.properties"))
@TestPropertySource(locations = arrayOf("classpath:application-test.properties"))
class PlantApplicationTests {

    private val jsonType = MediaType(APPLICATION_JSON.type, APPLICATION_JSON.subtype, Charset.forName("utf8"))

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var appUserRepository: AppUserRepository

    @Autowired
    private lateinit var tokenUtils: TokenUtils

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Before
    @Throws(Exception::class)
    fun setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun helloGuestTest() {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(jsonType))
                .andExpect(jsonPath("$.response", `is`("Hello, guest")))
                .andExpect(jsonPath("$.state", `is`("ok")))
    }

    @Test
    fun testUserRepo() {
        val findAll = appUserRepository.findAll();
        Assert.assertThat(findAll.size, `is`(2))
    }

    @Test
    fun authTest() {
        val writeValueAsString = objectMapper.writeValueAsString(AuthRequest("admin", "password"));
        val request = post("/auth")
                .content(writeValueAsString)
                .contentType(jsonType)
        mockMvc.perform(request)
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk)
                .andExpect(content().contentType(jsonType))
                .andDo { andValidateToken(it.response.contentAsString, "admin") }
    }

    private fun andValidateToken(token: String, login: String) {
        val typeRef = object : TypeReference<HashMap<String, String>>(){}
        val readValue = objectMapper.readValue<Map<String, String>>(token, typeRef)

        val appUser = appUserRepository.findUserByLogin(login)
        Assert.assertTrue(tokenUtils.tokenIsValid(readValue.get("token")!!, CurrentUser(appUser)))
    }
}
