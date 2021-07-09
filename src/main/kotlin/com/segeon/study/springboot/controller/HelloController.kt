package com.segeon.study.springboot.controller

import com.segeon.study.springboot.dto.HelloResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @GetMapping("/Hello")
    fun hello(): String {
        return "hello"
    }

    @GetMapping("/Hello/dto")
    fun helloDto(
        @RequestParam("name") name: String,
        @RequestParam("amount") amount: Int
    ): HelloResponseDto {
        return HelloResponseDto(name, amount)
    }
}