package se.rest.services

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import se.rest.repositories.UserEntity
import se.rest.repositories.UserRepository
import java.util.*
import java.util.concurrent.CompletableFuture

internal class UserServiceTest {

    private lateinit var userRepository: UserRepository

    @BeforeEach
    internal fun setUp() {
        userRepository = mockk()
    }

    @Test
    internal fun testGetUsers() {
        val user = UserEntity(
                id = UUID.randomUUID(),
                name = "El Dorado"
        )
        every { userRepository.getUsers() } returns
                CompletableFuture.completedFuture(listOf(user))

        val userService = UserService(userRepository)
        val users = userService.getUsers()

        assertEquals(listOf(User(user.id.toString(), user.name)), users)
    }
}
