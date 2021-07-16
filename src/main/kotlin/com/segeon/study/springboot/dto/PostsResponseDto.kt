package com.segeon.study.springboot.dto

data class PostsResponseDto(
    var id: Long? = null,
    var title: String,
    var content: String,
    var author: String? = null
)