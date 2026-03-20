package com.taskflow.repository

import com.taskflow.models.ProjectMember
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface ProjectMemberRepository : JpaRepository<ProjectMember, Long>
