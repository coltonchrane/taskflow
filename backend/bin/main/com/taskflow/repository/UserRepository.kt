package com.taskflow.repository

import com.taskflow.models.User
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface UserRepository : JpaRepository<User, Long>
