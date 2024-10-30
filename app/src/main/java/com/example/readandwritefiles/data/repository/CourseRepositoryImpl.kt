package com.example.readandwritefiles.data.repository

import com.example.readandwritefiles.data.data_source.MyDbDao
import com.example.readandwritefiles.domain.model.Course
import com.example.readandwritefiles.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class CourseRepositoryImpl(
    private val dao: MyDbDao
): CourseRepository {
    override fun getCourses(): Flow<List<Course>> {
        return dao.getCourses()
    }

    override suspend fun insertCourse(course: Course) {
        return dao.insertCourse(course)
    }
}