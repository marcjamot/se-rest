package se.rest.controllers

import io.quarkus.test.junit.QuarkusTest
import io.quarkus.test.junit.mockito.InjectMock
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import se.rest.services.User
import se.rest.services.UserService

@QuarkusTest
class UserControllerTest {

    @InjectMock
    lateinit var userService: UserService

    @Test
    fun testGetUsers() {
        val input = listOf(User("id", "name"))
        `when`(userService.getUsers()).thenReturn(input)

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
