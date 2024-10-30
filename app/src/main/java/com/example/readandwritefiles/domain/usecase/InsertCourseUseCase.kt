package com.example.readandwritefiles.domain.usecase

import com.example.readandwritefiles.domain.model.Course
import com.example.readandwritefiles.domain.repository.CourseRepository
import java.io.IOException

class InsertCourseUseCase(
    private val repository: CourseRepository
) {

    @Throws(IOException::class)
    suspend operator fun invoke(course: Course) {

        if (course.id.isBlank()) {
            throw IOException("The title of the note can't be empty.")
        }

        if (course.name.isBlank()) {
            throw IOException("The title of the note can't be empty.")
        }
        if (course.teacher.isBlank()) {
            throw IOException("The content of the note can't be empty.")
        }
        repository.insertCourse(course)
    }

}
