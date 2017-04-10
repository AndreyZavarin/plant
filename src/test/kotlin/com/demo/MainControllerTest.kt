package com.demo

import org.hamcrest.Matchers
import org.junit.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


class MainControllerTest : AbstractIntegrationTest() {

    @Test
    fun helloGuestTest() {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(jsonType))
                .andExpect(MockMvcResultMatchers.jsonPath("$.response", Matchers.`is`("Hello, guest")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.state", Matchers.`is`("ok")))
    }

}