package com.plugins

import com.routs.configureRoutingTest
import com.routs.configureRoutingUser
import com.routs.configureRoutingWord
import io.ktor.server.application.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

fun Application.configureRouting(parentRoute: String) {
    configureRoutingUser(parentRoute)
    configureRoutingTest(parentRoute)
    configureRoutingWord(parentRoute)
    routing {
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")
    }
}

