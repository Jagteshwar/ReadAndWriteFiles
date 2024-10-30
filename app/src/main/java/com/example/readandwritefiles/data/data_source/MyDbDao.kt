package com.example.readandwritefiles.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.readandwritefiles.domain.model.Course
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDbDao {

    @Query("SELECT * FROM course")
    fun getCourses(): Flow<List<Course>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Course)
}