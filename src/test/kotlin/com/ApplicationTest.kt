package com

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import com.routs.configureRoutingUser

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRoutingUser()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}
