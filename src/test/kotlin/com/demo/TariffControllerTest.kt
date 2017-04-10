package com.demo

import com.demo.dto.TariffDto
import com.demo.models.enums.TariffType
import org.junit.Test
import org.springframework.http.HttpMethod
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

class TariffControllerTest : AbstractIntegrationTest() {

    @Test
    fun readTariff() {
        val (_, token) = getUserAndHisToken("admin");

        val request = MockMvcRequestBuilders
                .request(HttpMethod.GET, "/tariff/1")
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(jsonType))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Is.`is`("BOZHEV")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Is.`is`("ALEXEY")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.middleName", Is.`is`("IGOREVICH")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.gender", Is.`is`("MALE")))
        //.andExpect(MockMvcResultMatchers.jsonPath("$.birthDate", `is`(LocalDate.now())))
    }

    @Test
    fun createTariff() {
        val (_, token) = getUserAndHisToken("admin");

        val tariffDto = TariffDto(-1, "ТЕСТОВЫЙ", BigDecimal.TEN, "Тестовый тариф", TariffType.REGULAR, 10, TimeUnit.DAYS.toMillis(7))
        val jsonBody = objectMapper.writeValueAsString(tariffDto)

        val request = MockMvcRequestBuilders
                .request(HttpMethod.POST, "/tariff/")
                .content(jsonBody)
                .contentType(jsonType)
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
    }

//    @Test
//    fun updateTariff() {
//        val (_, token) = getUserAndHisToken("admin");
//        val writeValueAsString = objectMapper.writeValueAsString(TariffDto(1, "ANDREY", "ODINOKOV", "ALEXANDROVICH", Gender.MALE, LocalDate.of(1992, Month.JUNE, 16)))
//
//        val jsonBody = writeValueAsString
//
//        val request = MockMvcRequestBuilders
//                .request(HttpMethod.POST, "/tariff/1")
//                .content(jsonBody)
//                .contentType(jsonType)
//                .header(tokenUtils.tokenHeader, token)
//
//        mockMvc.perform(request)
//                .andDo(MockMvcResultHandlers.print())
//    }

}