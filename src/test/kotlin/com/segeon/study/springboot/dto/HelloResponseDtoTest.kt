package com.segeon.study.springboot.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class HelloResponseDtoTest {

    @Test
    fun dto_Operate_Test() {
        // given
        val name = "test"
        val amount = 1000

        // when
        val dto = HelloResponseDto(name, amount)

        // then
        assertThat(dto.name).isEqualTo(name)
        assertThat(dto.amount).isEqualTo(amount)
    }
}