package com.taskflow.controller

import com.taskflow.models.Label
import com.taskflow.service.LabelService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/labels")
class LabelController(private val labelService: LabelService) {

    @Get("/")
    fun listLabels(): Iterable<Label> = labelService.findAll()

    @Get("/{id}")
    fun getLabel(id: Long): HttpResponse<Label> {
        val label = labelService.findById(id)
        return if (label != null) {
            HttpResponse.ok(label)
        } else {
            HttpResponse.notFound()
        }
    }

    @Post("/")
    fun createLabel(@Body label: Label): HttpResponse<Label> {
        val savedLabel = labelService.save(label)
        return HttpResponse.created(savedLabel)
    }

    @Delete("/{id}")
    fun deleteLabel(id: Long): HttpResponse<Unit> {
        labelService.delete(id)
        return HttpResponse.noContent()
    }
}
