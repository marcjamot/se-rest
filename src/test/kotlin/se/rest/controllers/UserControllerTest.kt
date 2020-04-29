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
import se.rest.services.User
import se.rest.services.UserService

@QuarkusTest
internal class UserControllerTest {

    private lateinit var userService: UserService

    @BeforeEach
    internal fun setUp() {
        userService = mockk()
        QuarkusMock.installMockForType(userService, UserService::class.java)
    }

    @Test
    internal fun testGetUsers() {
        val input = listOf(User("id", "name"))
        every { userService.getUsers() } returns input

        val output =
                When {
                    get("/users")
                } Then {
                    statusCode(200)
                } Extract {
                    `as`(UserController.GetUsersResponse::class.java)
                }

        assertEquals(input, output.users)
    }
}
