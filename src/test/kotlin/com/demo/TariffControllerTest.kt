package com.demo

import com.demo.dto.TariffDto
import com.demo.models.enums.TariffType
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.springframework.http.HttpMethod
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation
import org.springframework.restdocs.snippet.Snippet
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

class TariffControllerTest : AbstractIntegrationTest() {

    private val tariffFields = getTariffFields()

    private val tariffDto = TariffDto(-1, "НОВЫЙ", BigDecimal.TEN, "Новый тариф", TariffType.REGULAR, 10, TimeUnit.DAYS.toMillis(7))
    private val demoDto = TariffDto(-1, "ПРОБНЫЙ", BigDecimal.ZERO, "Пробный тариф", TariffType.EXPRESS, 1, TimeUnit.DAYS.toMillis(1))

    @Test
    fun readTariff() {
        val (_, token) = getUserAndHisToken("admin");

        val request = RestDocumentationRequestBuilders
                .get("/tariff/{id}", 1)
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())

                .andExpect(status().isOk)
                .andExpect(content().contentType(jsonType))

                .andExpect(jsonPath("$.id", `is`(1)))
                .andExpect(jsonPath("$.name", `is`("Тестовый")))
                .andExpect(jsonPath("$.description", `is`("Тестовый тариф")))
                .andExpect(jsonPath("$.type", `is`(TariffType.REGULAR.name)))
                .andExpect(jsonPath("$.quantity", `is`(10)))
                .andExpect(jsonPath("$.cost", `is`(666.0)))
                .andExpect(jsonPath("$.lifetimeInMillis", `is`(900000000)))

                .andDo(document("api/tariff/read",
                        getIdPathParameterSnippet(),
                        getRequestHeaderSnippet(),
                        responseFields(*tariffFields)))
    }

    @Test
    fun createTariff() {
        val (_, token) = getUserAndHisToken("admin");

        val jsonBody = objectMapper.writeValueAsString(tariffDto)

        val request = RestDocumentationRequestBuilders
                .request(HttpMethod.POST, "/tariff/")
                .content(jsonBody)
                .contentType(jsonType)
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())

                .andExpect(status().isOk)
                .andExpect(content().contentType(jsonType))

                .andExpect(jsonPath("$.id", `is`(3)))
                .andExpect(jsonPath("$.name", `is`("НОВЫЙ")))
                .andExpect(jsonPath("$.description", `is`("Новый тариф")))
                .andExpect(jsonPath("$.type", `is`(TariffType.REGULAR.name)))
                .andExpect(jsonPath("$.quantity", `is`(10)))
                .andExpect(jsonPath("$.cost", `is`(10)))
                .andExpect(jsonPath("$.lifetimeInMillis", `is`(604800000)))

                .andDo(document("api/tariff/create",
                        getRequestHeaderSnippet(),
                        responseFields(*tariffFields),
                        requestFields(*tariffFields)
                ))
    }

    @Test
    fun updateTariff() {
        val (_, token) = getUserAndHisToken("admin");

        val jsonBody = objectMapper.writeValueAsString(demoDto)
        val request = RestDocumentationRequestBuilders
                .post("/tariff/{id}", 2)
                .content(jsonBody)
                .contentType(jsonType)
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())

                .andExpect(status().isOk)
                .andExpect(content().contentType(jsonType))

                .andExpect(jsonPath("$.id", `is`(2)))
                .andExpect(jsonPath("$.name", `is`("ПРОБНЫЙ")))
                .andExpect(jsonPath("$.description", `is`("Пробный тариф")))
                .andExpect(jsonPath("$.type", `is`(TariffType.EXPRESS.name)))
                .andExpect(jsonPath("$.quantity", `is`(1)))
                .andExpect(jsonPath("$.cost", `is`(0)))
                .andExpect(jsonPath("$.lifetimeInMillis", `is`(86400000)))

                .andDo(document("api/tariff/update",
                        getIdPathParameterSnippet(),
                        requestFields(*tariffFields),
                        responseFields(*tariffFields),
                        getRequestHeaderSnippet()
                ))
    }

    private fun getTariffFields(prefix: String = ""): Array<FieldDescriptor> {
        val id = PayloadDocumentation.fieldWithPath(prefix + "id").description("Tariff id")
        val name = PayloadDocumentation.fieldWithPath(prefix + "name").description("Tariff name")
        val cost = PayloadDocumentation.fieldWithPath(prefix + "cost").description("Tariff cost")
        val description = PayloadDocumentation.fieldWithPath(prefix + "description").description("Tariff description")
        val type = PayloadDocumentation.fieldWithPath(prefix + "type").description("Tariff type")
        val quantity = PayloadDocumentation.fieldWithPath(prefix + "quantity").description("Tariff quantity")
        val lifetimeInMillis = PayloadDocumentation.fieldWithPath(prefix + "lifetimeInMillis").description("Tariff life time (in ms)")

        return arrayOf(name, cost, description, type, quantity, lifetimeInMillis, id)
    }

    private fun getIdPathParameterSnippet(): Snippet {
        return RequestDocumentation.pathParameters(RequestDocumentation.parameterWithName("id").description("The tariff id"))
    }

}