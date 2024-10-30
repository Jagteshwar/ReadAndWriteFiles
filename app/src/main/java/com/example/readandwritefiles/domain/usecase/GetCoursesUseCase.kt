package com.example.readandwritefiles.domain.usecase

import com.example.readandwritefiles.domain.model.Course
import com.example.readandwritefiles.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class GetCoursesUseCase(
    private val repository: CourseRepository
) {

    operator fun invoke(): Flow<List<Course>>{
        return repository.getCourses()
    }
}