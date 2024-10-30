package com.example.readandwritefiles.di

import android.app.Application
import androidx.room.Room
import com.example.readandwritefiles.data.data_source.MyDatabase
import com.example.readandwritefiles.data.repository.CourseRepositoryImpl
import com.example.readandwritefiles.domain.repository.CourseRepository
import com.example.readandwritefiles.domain.usecase.GetCoursesUseCase
import com.example.readandwritefiles.domain.usecase.InsertCourseUseCase
import com.example.readandwritefiles.presentation.viewmodel.AddCourseViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCourseDatabase(app: Application): MyDatabase {
        return Room.databaseBuilder(
            app,
            MyDatabase::class.java,
            MyDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideCourseRepository(db: MyDatabase): CourseRepository{
        return CourseRepositoryImpl(db.myDbDao)
    }

    @Provides
    @Singleton
    fun provideGetCoursesUseCase(repository: CourseRepository): GetCoursesUseCase{
        return GetCoursesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideInsertCourseUseCase(repository: CourseRepository): InsertCourseUseCase{
        return InsertCourseUseCase(repository)
    }
}