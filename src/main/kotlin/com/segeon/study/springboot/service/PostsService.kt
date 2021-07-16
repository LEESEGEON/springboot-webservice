package com.segeon.study.springboot.service

import com.segeon.study.springboot.domain.posts.Posts
import com.segeon.study.springboot.domain.posts.PostsRepository
import com.segeon.study.springboot.dto.PostsResponseDto
import com.segeon.study.springboot.dto.PostsSaveRequestDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostsService {

    @Autowired
    lateinit var postsRepository: PostsRepository

    @Transactional
    fun save(requestDto: PostsSaveRequestDto): Long? {

        return postsRepository.save(
            Posts(
                title = requestDto.title,
                content = requestDto.content,
                author = requestDto.author
            )
        ).id
    }

    @Transactional
    fun update(id: Long, requestDto: PostsSaveRequestDto): Long {

        val posts = postsRepository.findById(id).orElseThrow { IllegalArgumentException("해당 사용자가 없습니다. id=$id") }
        posts.update(requestDto.title, requestDto.content)

        return id
    }

    fun findById(id: Long): PostsResponseDto {

        val entity = postsRepository.findById(id).orElseThrow { IllegalArgumentException("해당 사용자가 없습니다. id=$id") }

        return PostsResponseDto(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            author = entity.author
        )
    }
}