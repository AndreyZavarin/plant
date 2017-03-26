package com.demo

import com.demo.repositories.AppUserRepository
import org.hamcrest.Matchers.`is`
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext
import java.nio.charset.Charset

@RunWith(SpringRunner::class)
@SpringBootTest(properties = arrayOf("application-test.properties"))
@TestPropertySource(locations = arrayOf("classpath:application-test.properties"))
class PlantApplicationTests {

    private val contentType = MediaType(APPLICATION_JSON.type, APPLICATION_JSON.subtype, Charset.forName("utf8"))

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var appUserRepository: AppUserRepository

    @Before
    @Throws(Exception::class)
    fun setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun helloGuestTest() {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.response", `is`("Hello, guest")))
                .andExpect(jsonPath("$.state", `is`("ok")))
    }

    @Test
    fun testUserRepo() {
        val findAll = appUserRepository.findAll();
        println("findAll = ${findAll}")
    }

}
