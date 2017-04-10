package com.demo

import com.demo.dto.ClientDto
import com.demo.models.enums.Gender
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.springframework.http.HttpMethod
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate
import java.time.Month

//todo
class ClientControllerTest : AbstractIntegrationTest() {

    @Test
    fun readClient() {
        val (_, token) = getUserAndHisToken("admin");

        val request = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/client/1")
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk)
                .andExpect(content().contentType(jsonType))
                .andExpect(jsonPath("$.lastName", `is`("BOZHEV")))
                .andExpect(jsonPath("$.firstName", `is`("ALEXEY")))
                .andExpect(jsonPath("$.middleName", `is`("IGOREVICH")))
                .andExpect(jsonPath("$.gender", `is`("MALE")))
        //.andExpect(MockMvcResultMatchers.jsonPath("$.birthDate", `is`(LocalDate.now())))
    }

    @Test
    fun createClient() {
        val (_, token) = getUserAndHisToken("admin");
        val jsonBody = objectMapper.writeValueAsString(ClientDto(-1, "NIKITA", "BOZHEV", "IGOREVICH", Gender.MALE, LocalDate.of(2002, Month.JUNE, 23)))

        val request = MockMvcRequestBuilders
                .request(HttpMethod.POST, "/client/")
                .content(jsonBody)
                .contentType(jsonType)
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andDo(print())
    }

    @Test
    fun updateClient() {
        val (_, token) = getUserAndHisToken("admin");
        val jsonBody = objectMapper.writeValueAsString(ClientDto(1, "ANDREY", "ODINOKOV", "ALEXANDROVICH", Gender.MALE, LocalDate.of(1992, Month.JUNE, 16)))

        val request = MockMvcRequestBuilders
                .request(HttpMethod.POST, "/client/1")
                .content(jsonBody)
                .contentType(jsonType)
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andDo(print())
    }

}