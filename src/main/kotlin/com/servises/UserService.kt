package com.servises


import com.dao.user.UserRepository
import com.dao.user.UserRepositoryMongoImpl
import com.models.ExposedUser

class UserService {
    // вот это меня смущает. Я компонентов не нашел в ktor мб нафиг историю с интерфейсами
    // но в документашки аналогично создавали
    private val userRepository: UserRepository = UserRepositoryMongoImpl()

    suspend fun findAllUsers(): List<ExposedUser> = userRepository.findAllUsers()
    suspend fun findUserByLogin(login:String): ExposedUser  = userRepository.findUserByLogin(login)
    suspend fun save(user: ExposedUser) = userRepository.save(user)
    suspend fun update(user: ExposedUser):Boolean = userRepository.update(user)
    suspend fun delete(id: String):Boolean = userRepository.delete(id)
}