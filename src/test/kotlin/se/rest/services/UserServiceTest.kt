package se.rest.services

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import se.rest.repositories.UserEntity
import se.rest.repositories.UserRepository
import java.util.*
import java.util.concurrent.CompletableFuture

internal class UserServiceTest {

    @Test
    internal fun testGetUsers() {
        val userRepository = mock(UserRepository::class.java)
        val user = UserEntity(
                id = UUID.randomUUID(),
                name = "El Dorado"
        )
        `when`(userRepository.getUsers())
                .thenReturn(CompletableFuture.completedFuture(listOf(user)))

        val userService = UserService(userRepository)
        val users = userService.getUsers()

        assertEquals(listOf(User(user.id.toString(), user.name)), users)
    }
}
