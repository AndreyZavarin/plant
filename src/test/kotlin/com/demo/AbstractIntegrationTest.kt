package com.demo

import com.demo.configs.TokenUtils
import com.demo.models.AppUser
import com.demo.repositories.AppUserRepository
import com.demo.services.auth.CurrentUser
import com.demo.services.auth.getUserDetails
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.JUnitRestDocumentation
import org.springframework.restdocs.headers.HeaderDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.snippet.Snippet
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext
import java.nio.charset.Charset
import java.util.*

@RunWith(SpringRunner::class)
@SpringBootTest()
@TestPropertySource(locations = arrayOf("classpath:application-test.properties"))
abstract class AbstractIntegrationTest {

    @Rule @JvmField
    val restDocumentation = JUnitRestDocumentation("target/generated-snippets")

    val jsonType = MediaType(APPLICATION_JSON.type, APPLICATION_JSON.subtype, Charset.forName("utf8"))

    @Autowired
    lateinit var objectMapper: ObjectMapper

    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var tokenUtils: TokenUtils

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var appUserRepository: AppUserRepository

    @Before
    @Throws(Exception::class)
    fun setup() {
        this.mockMvc = webAppContextSetup(webApplicationContext)
                .apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity())
                .apply<DefaultMockMvcBuilder>(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .build()
    }

    protected fun String.getTokenFromJson(): String {
        val typeRef = object : TypeReference<HashMap<String, String>>() {}
        val readValue = objectMapper.readValue<Map<String, String>>(this, typeRef)
        return readValue.get("token")!!
    }

    protected fun getUserAndHisToken(login: String): Pair<AppUser, String> {
        val user = appUserRepository.findUserByLogin(login)
        return user to generateValidToken(user.getUserDetails())
    }

    protected fun generateValidToken(userDetails: UserDetails): String {
        val generatedToken = tokenUtils.generateToken(userDetails)
        Assert.assertTrue(tokenUtils.tokenIsValid(generatedToken, userDetails))
        return generatedToken
    }

    protected fun validateToken(token: String, login: String) {
        val appUser = appUserRepository.findUserByLogin(login)
        Assert.assertTrue(tokenUtils.tokenIsValid(token, CurrentUser(appUser)))
    }

    protected fun getRequestHeaderSnippet(): Snippet {
        val headerDescriptor = HeaderDocumentation.headerWithName(tokenUtils.tokenHeader).description("User's JWT");
        val requestHeaders = HeaderDocumentation.requestHeaders(headerDescriptor)
        return requestHeaders
    }
}
