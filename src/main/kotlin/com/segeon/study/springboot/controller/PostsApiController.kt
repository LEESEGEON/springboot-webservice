package com.segeon.study.springboot.controller

import com.segeon.study.springboot.dto.PostsResponseDto
import com.segeon.study.springboot.dto.PostsSaveRequestDto
import com.segeon.study.springboot.service.PostsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class PostsApiController {

    @Autowired
    lateinit var postsService: PostsService

    @PostMapping("/api/v1/posts")
    fun save(@RequestBody requestDto: PostsSaveRequestDto): Long? {

        return postsService.save(requestDto)
    }

    @PutMapping("/api/v1/posts/{id}")
    fun update(@PathVariable id: Long, @RequestBody requestDto: PostsSaveRequestDto): Long {

        return postsService.update(id, requestDto)
    }

    @GetMapping("/api/v1/posts/{id}")
    fun findById(@PathVariable id: Long): PostsResponseDto {

        return postsService.findById(id)
    }
}