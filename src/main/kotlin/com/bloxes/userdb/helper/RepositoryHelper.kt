package com.bloxes.userdb.helper

import com.bloxes.userdb.entity.User
import com.bloxes.userdb.repository.UserRepository
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class RepositoryHelper(private val userRepository: UserRepository) {
    fun findProductByIdOrThrowNotFound(id: String): User {
        val user = userRepository.findByIdOrNull(id)
        if (user == null) {
            throw ChangeSetPersister.NotFoundException()
        } else {
            return user;
        }
    }
}