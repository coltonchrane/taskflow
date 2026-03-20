package com.taskflow.service

import com.taskflow.models.Label
import com.taskflow.repository.LabelRepository
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
open class LabelService(private val labelRepository: LabelRepository) {

    fun findAll(): Iterable<Label> = labelRepository.findAll()

    fun findById(id: Long): Label? = labelRepository.findById(id).orElse(null)

    fun save(label: Label): Label {
        if (label.id == null) {
            label.createdAt = LocalDateTime.now()
        }
        return labelRepository.save(label)
    }

    fun delete(id: Long) {
        labelRepository.deleteById(id)
    }
}
