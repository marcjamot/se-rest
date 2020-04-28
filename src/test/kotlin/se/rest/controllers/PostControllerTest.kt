package se.rest.controllers

import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.mockito.InjectMock
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import se.rest.services.BlogService
import se.rest.services.Post

@QuarkusTest
class PostControllerTest {

    @InjectMock
    internal lateinit var blogService: BlogService

    @Test
    internal fun testIndex() {
        val input = listOf(Post("id", "user_id", "content"))
        `when`(blogService.getPosts()).thenReturn(input)

        val output =
                When {
                    get("/posts")
                } Then {
                    statusCode(200)
                } Extract {
                    `as`(PostController.GetPostsResponse::class.java)
                }

        assertEquals(input, output.posts)
    }
}
