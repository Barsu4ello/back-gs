package com.dao.user

import com.models.ExposedUser


interface UserRepository {
    suspend fun findAllUsers(): List<ExposedUser>
    suspend fun findUserByLogin(login: String): ExposedUser
    suspend fun save(user: ExposedUser)
    suspend fun update(user: ExposedUser):Boolean
    suspend fun delete(login: String): Boolean
}