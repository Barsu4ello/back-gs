package com.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class ExposedWord(val id: Long = 0, val english: String, val russian: String)

object Words : Table() {
    val id = long("id").autoIncrement()
    val english = varchar("english", length = 128)
    val russian = varchar("russian", length = 128)

    override val primaryKey = PrimaryKey(id)
}