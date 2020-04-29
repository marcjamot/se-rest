package se.rest.services

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import se.rest.repositories.BlogRepository
import se.rest.repositories.PostEntity
import java.util.*
import java.util.concurrent.CompletableFuture

internal class BlogServiceTest {

    private lateinit var blogRepository: BlogRepository

    @BeforeEach
    internal fun setUp() {
        blogRepository = mockk()
    }

    @Test
    internal fun testGetPosts() {
        val post = PostEntity(
                id = UUID.randomUUID(),
                userId = UUID.randomUUID(),
                content = "My post"
        )
        every { blogRepository.getPosts() } returns
                CompletableFuture.completedFuture(listOf(post))

        val blogService = BlogService(blogRepository)
        val posts = blogService.getPosts()

        assertEquals(listOf(Post(post.id.toString(), post.userId.toString(), post.content)), posts)
    }
}
