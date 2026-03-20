package com.taskflow.repository

import com.taskflow.models.Project
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface ProjectRepository : JpaRepository<Project, Long>
