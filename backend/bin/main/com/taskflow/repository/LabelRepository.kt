package com.taskflow.repository

import com.taskflow.models.Label
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface LabelRepository : JpaRepository<Label, Long>
