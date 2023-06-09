package com.routs

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// Пока все через words делаем
fun Application.configureRoutingTest(parentRoute: String) {
    routing {
        route(parentRoute) {
            route("/tests") {
                get() {


                }
                get("/{id}") {

                }
                post {

                }
                put {

                }
                delete("/{id}") {

                }
            }
        }
    }
}