package com.bloxes.userdb.repository

import com.bloxes.userdb.entity.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User, String> {

}