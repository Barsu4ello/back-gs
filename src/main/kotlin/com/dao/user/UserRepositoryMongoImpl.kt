package com.dao.user

import com.models.ExposedUser
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.UpdateResult
import com.plugins.MongoDBFactory
import io.ktor.server.plugins.*
import org.litote.kmongo.eq

class UserRepositoryMongoImpl : UserRepository{

    val col = MongoDBFactory.database.getCollection<ExposedUser>("users") //KMongo extension method

    override suspend fun findAllUsers(): List<ExposedUser> {
        return col.find().toList()
    }

    override suspend fun findUserByLogin(login: String): ExposedUser {
        return col.findOne(ExposedUser::login eq login)
            ?: throw NotFoundException("User with login: $login not found")
    }

    override suspend fun save(user: ExposedUser) {
        col.save(user)
    }

    override suspend fun update(user: ExposedUser): Boolean {
       val updateRes:UpdateResult = col.updateOne("{login:'${user.login}'}", "{\$set:{login:'${user.login}',password:'${user.password}'}}")
        if (updateRes.modifiedCount > 0){
            return true
        }
        return false
    }

    override suspend fun delete(login: String): Boolean {
        val deleteRes:DeleteResult = col.deleteOne("{login:'${login}'}")
        if (deleteRes.deletedCount > 0){
            return true
        }
        return false
    }


}