package com.example.readandwritefiles.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.readandwritefiles.domain.model.Course
import com.example.readandwritefiles.domain.usecase.ReadAndWriteFileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.InputStream


@HiltViewModel
class ReadWriteFileViewModel : ViewModel() {


    val readAndWriteUseCase = ReadAndWriteFileUseCase()
    private var _state = MutableLiveData<List<Course>>()
    val state: LiveData<List<Course>> = _state

    private var _listSizeState = MutableLiveData<Int>(0)
    val listSizeState: LiveData<Int> = _listSizeState

    val courseList = mutableListOf<Course>()

    private val _dataSaved = MutableLiveData<Boolean>(false)
    val savedData: LiveData<Boolean> = _dataSaved

    fun viewData(context: Context, fileName: String) {
           //_state.postValue(parseFile(context, fileName)
        _state.postValue(readAndWriteUseCase.parseFile(context, fileName))
    }



    @SuppressLint("NullSafeMutableLiveData")
    fun removeList() {
        _state.postValue(null)
    }

    fun saveParsedData(data: List<Course>, outputPath: String, format: String):Boolean {
        return readAndWriteUseCase.saveParsedData(data, outputPath, format)
    }

    fun addCourse(course: Course){
        courseList.forEach{
            courseList.contains(course)
            }
        courseList.add(course)
    //    _state.postValue(courseList)
    }


}