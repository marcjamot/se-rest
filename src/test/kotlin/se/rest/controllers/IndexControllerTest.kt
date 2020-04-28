package se.rest.controllers

import io.quarkus.test.junit.QuarkusTest
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@QuarkusTest
internal class IndexControllerTest {

    @Test
    internal fun testIndex() {
        val output =
                When {
                    get("/")
                } Then {
                    statusCode(200)
                } Extract {
                    `as`(ResponseMessage::class.java)
                }

        assertEquals("It's working!", output.message)
    }
}
