package com.demo

import com.demo.dto.SubscriptionDto
import com.demo.repositories.ClientRepository
import com.demo.repositories.SubscriptionRepository
import com.demo.repositories.TariffRepository
import org.junit.FixMethodOrder
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@FixMethodOrder
class SubscriptionControllerTest() : AbstractIntegrationTest() {

    @Autowired
    lateinit var clientRepo: ClientRepository

    @Autowired
    lateinit var tariffRepo: TariffRepository

    @Autowired
    lateinit var subscriptionRepo: SubscriptionRepository

    @Test
    fun createSubscription() {
        val (_, token) = getUserAndHisToken("admin");

        val subscriptionDto = SubscriptionDto(1, 1)

        val jsonBody = objectMapper.writeValueAsString(subscriptionDto)
        val request = RestDocumentationRequestBuilders
                .request(HttpMethod.POST, "/subscription")
                .content(jsonBody)
                .contentType(jsonType)
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(jsonType))
                .andDo(MockMvcResultHandlers.print())
//
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.`is`(3)))
//
//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Is.`is`(juniorData.lastName)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Is.`is`(juniorData.firstName)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.middleName", Is.`is`(juniorData.middleName)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.gender", Is.`is`(juniorData.gender.name)))
//
//                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate[0]", Is.`is`(juniorData.birthDate.year)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate[1]", Is.`is`(juniorData.birthDate.monthValue)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate[2]", Is.`is`(juniorData.birthDate.dayOfMonth)))
    }

    @Test
    fun readSubscription() {
        val (_, token) = getUserAndHisToken("admin");

        val request = RestDocumentationRequestBuilders
                .get("/subscription/{id}", 1)
                .header(tokenUtils.tokenHeader, token)

        mockMvc.perform(request)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(jsonType))

//                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Is.`is`(alexData.lastName)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Is.`is`(alexData.firstName)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.middleName", Is.`is`(alexData.middleName)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.gender", Is.`is`(alexData.gender.name)))
//
//                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate[0]", Is.`is`(alexData.birthDate.year)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate[1]", Is.`is`(alexData.birthDate.monthValue)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.birthDate[2]", Is.`is`(alexData.birthDate.dayOfMonth)))
    }

}