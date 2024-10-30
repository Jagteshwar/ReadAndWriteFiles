package com.example.readandwritefiles.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.readandwritefiles.domain.usecase.InsertCourseUseCase

class UserViewModelFactory(private val insertCourse: InsertCourseUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddCourseViewModel::class.java)) {
            return AddCourseViewModel(insertCourse) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}