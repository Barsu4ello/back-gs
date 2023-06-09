package com.routs

import com.models.ExposedUser
import com.servises.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRoutingUser(parentRoute: String) {

    val userService = UserService()

    routing {
        route(parentRoute) {
            route("/users") {
                get {
                    val users = userService.findAllUsers()
                    call.respond(HttpStatusCode.OK, users)
                }

                get("/{login}") {
                    val login = call.parameters["login"]!!.toString()
                    val user = userService.findUserByLogin(login)
                    call.respond(HttpStatusCode.OK, user)
                }

                post {
                    val user = call.receive<ExposedUser>()
                    userService.save(user)
                    call.respondText("User with ${user.login} stored correctly", status = HttpStatusCode.Created)
                }

                put {
                    val user = call.receive<ExposedUser>()
                    val isAdded: Boolean = userService.update(user)
                    if (isAdded) {
                        call.respondText("User updated", status = HttpStatusCode.OK)
                    }
                    call.respondText("User with ${user.login} not found", status = HttpStatusCode.NotFound)
                }
                delete("/{login}") {
                    val login = call.parameters["login"]!!.toString()
                    val isRemoved: Boolean = userService.delete(login)
                    if (isRemoved) {
                        call.respondText("User removed", status = HttpStatusCode.OK)
                    }
                    call.respondText("User with $login not found", status = HttpStatusCode.NotFound)
                }
            }
        }
    }
}
