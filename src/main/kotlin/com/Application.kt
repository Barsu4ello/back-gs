package com

import com.plugins.*
import com.plugins.configureRouting
import io.ktor.server.application.*



fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {


    configureSecurity()
    configureHTTP()
    configureSerialization()
    configureCORS()
        // обязательно перед тем как роуты инициализируются
    MongoDBFactory.init(
        environment.config.property("ktor.mongo.clientUrl").getString(),
        environment.config.property("ktor.mongo.databaseName").getString(),
    )
    configureRouting(environment.config.property("ktor.route.parent").getString())
    DatabaseFactory.init(
        environment.config.property("ktor.jdbc.url").getString(),
        environment.config.property("ktor.jdbc.user").getString(),
        environment.config.property("ktor.jdbc.password").getString(),
        environment.config.property("ktor.jdbc.driver").getString()
    )
}

