package com.plugins

import com.models.Words
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init(url:String, user:String, password:String, driver:String) {
        val database: Database = connectToPostgres(url,user,password,driver)
        transaction(database) {
            SchemaUtils.create(Words)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}

fun connectToPostgres(url:String, user:String, password:String, driver:String): Database {
    Class.forName("org.postgresql.Driver")

    return Database.connect(
        url = url,
        user = user,
        password = password,
        driver = driver,
    )

}
