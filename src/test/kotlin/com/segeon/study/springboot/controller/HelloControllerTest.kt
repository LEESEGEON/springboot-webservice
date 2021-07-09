package com.segeon.study.springboot.controller

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@RunWith(SpringRunner::class)
@WebMvcTest(controllers = [HelloController::class])
class HelloControllerTest {

    @Autowired
    private lateinit var mvc: MockMvc

    @Test
    fun return_hello_Test() {
        val hello = "hello"

        mvc.perform(MockMvcRequestBuilders.get("/Hello"))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().string(hello))
    }

    @Test
    fun return_helloResponseDto_Test() {
        val name = "hello"
        val amount = 1000

        mvc.perform(MockMvcRequestBuilders.get("/Hello/dto")
            .param("name", name)
            .param("amount", amount.toString())
        )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(name))
                .andExpect(MockMvcResultMatchers.jsonPath("amount").value(amount))
    }
}