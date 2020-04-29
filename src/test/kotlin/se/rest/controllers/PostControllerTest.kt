package se.rest.controllers

import io.mockk.every
import io.mockk.mockk
import io.quarkus.test.junit.QuarkusMock
import io.quarkus.test.junit.QuarkusTest
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import se.rest.services.BlogService
import se.rest.services.Post

@QuarkusTest
internal class PostControllerTest {

    private lateinit var blogService: BlogService

    @BeforeEach
    internal fun setUp() {
        blogService = mockk()
        QuarkusMock.installMockForType(blogService, BlogService::class.java)
    }

    @Test
    internal fun testIndex() {
        val input = listOf(Post("id", "user_id", "content"))
        every { blogService.getPosts() } returns input

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
