package com.demo

import com.demo.dto.ClientDto
import com.demo.models.enums.Gender
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.springframework.http.HttpMethod
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation.*
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.pathParameters
import org.springframework.restdocs.snippet.Snippet
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate
import java.time.Month

class ClientControllerTest : AbstractIntegrationTest() {

    private val alexData = ClientDto(-1, "ALEXEY", "BOZHEV", "IGOREVICH", Gender.MALE, LocalDate.of(1996, Month.SEPTEMBER, 26), emptyList())
    private val juniorData = ClientDto(-1, "NIKITA", "BOZHEV", "IGOREVICH", Gender.MALE, LocalDate.of(2002, Month.JUNE, 23), emptyList())
    private val andrewData = ClientDto(2, "ANDREY", "ODINOKOV", "ALEXANDROVICH", Gender.MALE, LocalDate.of(1992, Month.JUNE, 16), emptyList())

    private val clientFields = getClientFields()

    @Test
    fun readClient() {
        val (_, token) = getUserAndHisToken("admin");

        val request = RestDocumentationRequestBuilders
                .get("/client/{id}", 1)
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andExpect(status().isOk)
                .andExpect(content().contentType(jsonType))

                .andExpect(jsonPath("$.lastName", `is`(alexData.lastName)))
                .andExpect(jsonPath("$.firstName", `is`(alexData.firstName)))
                .andExpect(jsonPath("$.middleName", `is`(alexData.middleName)))
                .andExpect(jsonPath("$.gender", `is`(alexData.gender.name)))

                .andExpect(jsonPath("$.birthDate[0]", `is`(alexData.birthDate.year)))
                .andExpect(jsonPath("$.birthDate[1]", `is`(alexData.birthDate.monthValue)))
                .andExpect(jsonPath("$.birthDate[2]", `is`(alexData.birthDate.dayOfMonth)))

                .andDo(document("api/client/{id}",
                        getIdPathParameterSnippet(),
                        getRequestHeaderSnippet(),
                        responseFields(*clientFields)))
    }

    @Test
    fun createClient() {
        val (_, token) = getUserAndHisToken("admin");

        val jsonBody = objectMapper.writeValueAsString(juniorData)
        val request = RestDocumentationRequestBuilders
                .request(HttpMethod.POST, "/client/")
                .content(jsonBody)
                .contentType(jsonType)
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andExpect(status().isOk)
                .andExpect(content().contentType(jsonType))

                .andExpect(jsonPath("$.id", `is`(3)))

                .andExpect(jsonPath("$.lastName", `is`(juniorData.lastName)))
                .andExpect(jsonPath("$.firstName", `is`(juniorData.firstName)))
                .andExpect(jsonPath("$.middleName", `is`(juniorData.middleName)))
                .andExpect(jsonPath("$.gender", `is`(juniorData.gender.name)))

                .andExpect(jsonPath("$.birthDate[0]", `is`(juniorData.birthDate.year)))
                .andExpect(jsonPath("$.birthDate[1]", `is`(juniorData.birthDate.monthValue)))
                .andExpect(jsonPath("$.birthDate[2]", `is`(juniorData.birthDate.dayOfMonth)))

                .andDo(document("api/client/",
                        requestFields(*clientFields),
                        responseFields(*clientFields),
                        getRequestHeaderSnippet()))
    }

    @Test
    fun updateClient() {
        val (_, token) = getUserAndHisToken("admin")

        val jsonBody = objectMapper.writeValueAsString(andrewData)
        val request = RestDocumentationRequestBuilders
                .post("/client/{id}", 2)
                .content(jsonBody)
                .contentType(jsonType)
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andExpect(status().isOk)
                .andExpect(content().contentType(jsonType))

                .andExpect(jsonPath("$.id", `is`(2)))
                .andExpect(jsonPath("$.lastName", `is`(andrewData.lastName)))
                .andExpect(jsonPath("$.firstName", `is`(andrewData.firstName)))
                .andExpect(jsonPath("$.middleName", `is`(andrewData.middleName)))
                .andExpect(jsonPath("$.gender", `is`(andrewData.gender.name)))

                .andExpect(jsonPath("$.birthDate[0]", `is`(andrewData.birthDate.year)))
                .andExpect(jsonPath("$.birthDate[1]", `is`(andrewData.birthDate.monthValue)))
                .andExpect(jsonPath("$.birthDate[2]", `is`(andrewData.birthDate.dayOfMonth)))

                .andDo(document("api/client/{id}",
                        getIdPathParameterSnippet(),
                        requestFields(*clientFields),
                        responseFields(*clientFields),
                        getRequestHeaderSnippet()))
    }

    @Test
    fun getAllClients() {
        val (_, token) = getUserAndHisToken("admin")

        val request = RestDocumentationRequestBuilders
                .get("/client/all")
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andExpect(status().isOk)
                .andExpect(content().contentType(jsonType))

                .andDo(document("api/client/getAll", relaxedResponseFields(
                        fieldWithPath("content").description("An array of clients").attributes(),
                        *getClientFields("content[].")
                )))
    }

    private fun getIdPathParameterSnippet(): Snippet {
        return pathParameters(parameterWithName("id").description("The client's id"))
    }

    private fun getClientFields(prefix: String = ""): Array<FieldDescriptor> {
        val firstName = fieldWithPath(prefix + "firstName").description("Client's firstName")
        val lastName = fieldWithPath(prefix + "lastName").description("Client's last name")
        val middleName = fieldWithPath(prefix + "middleName").description("Client's middle name")
        val birthDate = fieldWithPath(prefix + "birthDate").description("Client's birth date")
        val gender = fieldWithPath(prefix + "gender").description("Client's gender")
        val id = fieldWithPath(prefix + "id").description("The client's id in our system")
        val subscriptions = fieldWithPath(prefix + "subscriptions").description("Array of client's subscriptions")

        return arrayOf(firstName, lastName, middleName, birthDate, gender, id, subscriptions)
    }

}