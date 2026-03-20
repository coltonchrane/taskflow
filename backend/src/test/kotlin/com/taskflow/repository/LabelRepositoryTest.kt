package com.taskflow.repository

import com.taskflow.models.Label
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@MicronautTest
class LabelRepositoryTest {

    @Inject
    lateinit var labelRepository: LabelRepository

    @BeforeEach
    fun setup() {
        labelRepository.deleteAll()
    }

    @Test
    fun `should save and find label`() {
        val label = Label(name = "Bug", color = "#FF0000")
        val savedLabel = labelRepository.save(label)

        assertNotNull(savedLabel.id)
        
        val foundLabel = labelRepository.findById(savedLabel.id!!).orElse(null)
        assertNotNull(foundLabel)
        assertEquals("Bug", foundLabel?.name)
        assertEquals("#FF0000", foundLabel?.color)
    }

    @Test
    fun `should list labels`() {
        labelRepository.save(Label(name = "Feature", color = "#00FF00"))
        labelRepository.save(Label(name = "Urgent", color = "#0000FF"))

        val labels = labelRepository.findAll()
        assertEquals(2, labels.size)
    }

    @Test
    fun `should delete label`() {
        val label = Label(name = "To Delete")
        val savedLabel = labelRepository.save(label)
        
        labelRepository.deleteById(savedLabel.id!!)

        assertFalse(labelRepository.existsById(savedLabel.id!!))
    }
}
