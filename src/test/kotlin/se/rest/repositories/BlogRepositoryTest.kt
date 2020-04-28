package se.rest.repositories

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import se.rest.test.PostgreSQL
import java.util.*

@Testcontainers
internal class BlogRepositoryTest {

    @Container
    internal val postgreSQL = PostgreSQL().withDefaults()

    @BeforeEach
    internal fun setUp() {
        postgreSQL.setUp()
    }

    @Test
    internal fun testGetPosts() {
        val blogRepository = BlogRepository(postgreSQL.database)
        val posts = blogRepository.getPosts().get()
        assertEquals(listOf(PostEntity(
                UUID.fromString("2119477a-d12e-4533-b8ff-45e6a8ce4f75"),
                UUID.fromString("69fe3546-62a2-4bf7-a1d0-78654ecac2d7"),
                "This is an example post.")
        ), posts)
    }
}
