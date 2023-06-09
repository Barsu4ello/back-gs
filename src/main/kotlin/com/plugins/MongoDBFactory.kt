package com.plugins

import org.litote.kmongo.coroutine.CoroutineClient
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

object MongoDBFactory {
    lateinit var database: CoroutineDatabase

    fun init(clientUrl: String, databaseName: String) {
        val client: CoroutineClient = KMongo.createClient(clientUrl).coroutine
        database = client.getDatabase(databaseName)
    }
}