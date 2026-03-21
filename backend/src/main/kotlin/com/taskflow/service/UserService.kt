package com.taskflow.service

import com.taskflow.models.User
import com.taskflow.repository.UserRepository
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
open class UserService(private val userRepository: UserRepository) {

    fun findAll(): Iterable<User> = userRepository.findAll()

    fun findById(id: Long): User? = userRepository.findById(id).orElse(null)

    fun save(user: User): User {
        val toSave = if (user.id == null) {
            user.copy(createdAt = LocalDateTime.now(), updatedAt = LocalDateTime.now())
        } else {
            user.copy(updatedAt = LocalDateTime.now())
        }
        return userRepository.save(toSave)
    }

    fun delete(id: Long) {
        userRepository.deleteById(id)
    }

    fun update(id: Long, user: User): User? {
        val existingUser = findById(id) ?: return null
        val toUpdate = existingUser.copy(
            username = user.username,
            email = user.email,
            password = user.password,
            updatedAt = LocalDateTime.now()
        )
        return userRepository.update(toUpdate)
    }
}
