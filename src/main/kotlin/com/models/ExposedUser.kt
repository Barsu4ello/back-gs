package com.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class ExposedUser(val _id: String? = null, val login: String, val password: String)
