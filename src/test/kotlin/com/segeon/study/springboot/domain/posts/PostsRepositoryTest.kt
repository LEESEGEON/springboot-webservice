package com.segeon.study.springboot.domain.posts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostsRepositoryTest {

    @Autowired lateinit var postsRepository: PostsRepository

    @AfterAll
    fun cleanUp() {
        postsRepository.deleteAll()
    }

    @Test
    fun `게시글저장_불러오기`() {
        // given
        val title = "테스트 게시글"
        val content = "테스트 본문"

        postsRepository.save(
            Posts(
                title = title,
                content = content
            )
        )

        //when
        val postsList = postsRepository.findAll()

        // then
        val posts = postsList[0]
        assertThat(posts.title).isEqualTo(title)
        assertThat(posts.content).isEqualTo(content)
    }
}