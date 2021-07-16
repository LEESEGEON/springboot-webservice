package com.segeon.study.springboot.controller

import com.segeon.study.springboot.domain.posts.Posts
import com.segeon.study.springboot.domain.posts.PostsRepository
import com.segeon.study.springboot.dto.PostsSaveRequestDto
import com.segeon.study.springboot.dto.PostsUpdateRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostsApiControllerTest {

    @LocalServerPort
    private var port = 8000

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var postsRepository: PostsRepository

    @AfterAll
    fun tearDown() {
        postsRepository.deleteAll()
    }

    @Test
    fun `Posts_등록된다`() {

        // given
        val title = "title"
        val content = "content"
        val requestDto = PostsSaveRequestDto(
            title = title,
            content = content,
            author = "author"
        )

        val url = "http://localhost:$port/api/v1/posts"

        // when
        val responseEntity: ResponseEntity<Long> = restTemplate.postForEntity(url, requestDto, Long::class.java)

        // then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)

        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(title)
        assertThat(all[0].content).isEqualTo(content)
    }

    @Test
    fun `Posts_수정된다`() {

        // given
        val savedPosts = postsRepository.save(
            Posts(
                title = "title",
                content = "content",
                author = "author"
            )
        )

        val updateId = savedPosts.id
        val expectedTitle = "title2"
        val expectedContent = "content2"

        val requestDto = PostsUpdateRequestDto(title = expectedTitle, content = expectedContent)

        val url = "http://localhost:$port/api/v1/posts/$updateId"

        val requestEntity = HttpEntity<PostsUpdateRequestDto>(requestDto)

        // when
        val responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long::class.java)

        // then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)

        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(expectedTitle)
        assertThat(all[0].content).isEqualTo(expectedContent)
    }
}