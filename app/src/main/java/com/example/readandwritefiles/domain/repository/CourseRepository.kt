package com.example.readandwritefiles.domain.repository

import com.example.readandwritefiles.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    fun getCourses() : Flow<List<Course>>

    suspend fun insertCourse(course: Course)
}