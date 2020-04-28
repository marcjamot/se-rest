package se.rest.services

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import se.rest.repositories.BlogRepository
import se.rest.repositories.PostEntity
import java.util.*
import java.util.concurrent.CompletableFuture

internal class BlogServiceTest {

    @Test
    internal fun testGetPosts() {
        val blogRepository = mock(BlogRepository::class.java)
        val post = PostEntity(
                id = UUID.randomUUID(),
                userId = UUID.randomUUID(),
                content = "My post"
        )
        `when`(blogRepository.getPosts())
                .thenReturn(CompletableFuture.completedFuture(listOf(post)))

        val blogService = BlogService(blogRepository)
        val posts = blogService.getPosts()

        assertEquals(listOf(Post(post.id.toString(), post.userId.toString(), post.content)), posts)
    }
}
