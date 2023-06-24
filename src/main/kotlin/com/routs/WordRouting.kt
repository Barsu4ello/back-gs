package com.routs

import com.models.ExposedWord
import com.servises.WordService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRoutingWord(parentRoute: String) {

    val wordService = WordService()

    routing {
        route(parentRoute) {
            route("/words") {
                get() {
                    val words = wordService.findAllWords()
                    call.respond(HttpStatusCode.OK, words)
                }
                get("/{id}") {
                    val id = call.parameters["id"]!!.toLong()
                    val word = wordService.findWordById(id)
                    if (word != null) {
                        call.respond(HttpStatusCode.OK, word)
                    }
                    call.respond(HttpStatusCode.NotFound, "Word with $id not found")
                }

                post {
                    val word = call.receive<ExposedWord>()
                    val id: Long = wordService.save(word)
                    call.respond(HttpStatusCode.Created, id)
                }

                put("/{id}") {
                    val id = call.parameters["id"]!!.toLong()
                    val word = call.receive<ExposedWord>()
                    val isAdded: Boolean = wordService.update(id, word)
                    if (isAdded) {
                        call.respondText("Word updated", status = HttpStatusCode.OK)
                    }
                    call.respondText("Word with $id not found", status = HttpStatusCode.NotFound)
                }
                delete("/{id}") {
                    val id = call.parameters["id"]!!.toLong()
                    val isRemoved: Boolean = wordService.delete(id)
                    if (isRemoved) {
                        call.respondText("Word removed", status = HttpStatusCode.OK)
                    }
                    call.respondText("Word with $id not found", status = HttpStatusCode.NotFound)
                }
            }
        }
    }
}