package com.example.readandwritefiles.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.readandwritefiles.domain.model.Course
import com.example.readandwritefiles.domain.usecase.InsertCourseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AddCourseViewModel @Inject constructor(
    private val insertCourse: InsertCourseUseCase
): ViewModel() {

    init {
        viewModelScope.launch {
            try{
                insertCourse.invoke(Course(
                    id = "1",
                    name = "Math",
                    teacher = "Mr. Zee"
                ))
            }catch(e: IOException){
                println(e.message)
            }
        }


    }

    fun saveToDB(){
        viewModelScope.launch {
            try{
                insertCourse.invoke(Course(
                    id = "1",
                    name = "Math",
                    teacher = "Mr. Zee"
                ))
            }catch(e: IOException){
                println(e.message)
            }
        }
    }
}