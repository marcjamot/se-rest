package se.rest.repositories

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import se.rest.test.PostgreSQL
import java.util.*

@Testcontainers
internal class UserRepositoryTest {

    @Container
    internal val postgreSQL = PostgreSQL().withDefaults()

    @BeforeEach
    internal fun setUp() {
        postgreSQL.setUp()
    }

    @Test
    internal fun testGetUsers() {
        val userRepository = UserRepository(postgreSQL.database)
        val users = userRepository.getUsers().get()
        assertEquals(listOf(UserEntity(
                id = UUID.fromString("69fe3546-62a2-4bf7-a1d0-78654ecac2d7"),
                name = "Mr Kotlin")
        ), users)
    }
}
