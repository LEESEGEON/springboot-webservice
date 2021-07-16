package com.segeon.study.springboot.dto

data class PostsSaveRequestDto(
    var title: String,
    var content: String,
    var author: String? = null
)