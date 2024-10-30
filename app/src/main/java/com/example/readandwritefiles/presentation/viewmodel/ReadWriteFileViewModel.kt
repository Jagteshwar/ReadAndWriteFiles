package com.example.readandwritefiles.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.readandwritefiles.domain.model.Course
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

    private var _state = MutableLiveData<List<Course>>()
    val state: LiveData<List<Course>> = _state

    private var _listSizeState = MutableLiveData<Int>(0)
    val listSizeState: LiveData<Int> = _listSizeState

    val courseList = mutableListOf<Course>()

    fun viewData(context: Context, fileName: String) {
        parseFile(context, fileName)
    }

    fun parseFile(context: Context, fileName: String) {
         when (fileName.substringAfterLast(".")) {
            "json" -> parseJson(context, fileName)
            "csv" -> parseCsv(context, fileName)
            else -> {
                println("Unsupported file format")
             //   emptyList()
            }
        }
    }

    fun parseJson(context: Context, fileName: String) {
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonString)
        val courseList = mutableListOf<Course>()

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getString("id")
            val name = jsonObject.getString("name")
            val teacher = jsonObject.getString("teacher")
            courseList.add(Course(id, name, teacher))
        }
        _state.postValue(courseList)

    }

    fun parseCsv(context: Context, fileName: String) {

            courseList.clear()
            context.assets.open(fileName).bufferedReader().useLines { lines ->
                lines.forEach { line -> // Assuming the first line is a header
                    val values = line.split(",")
                    val id = values[0]
                    val name = values[1]
                    val teacher = values[2]
                    courseList.add(Course(id, name, teacher))
                }
        }
            _state.postValue(courseList)


    }

    @SuppressLint("NullSafeMutableLiveData")
    fun removeList() {
        _state.postValue(null)
    }

    fun saveParsedData(data: List<Course>, outputPath: String, format: String) {
        val file = File(outputPath)

        when (format) {
            "json" -> {
                val jsonArray = JSONArray()
                data.forEach { course ->
                    val jsonObject = JSONObject()
                    jsonObject.put("id", course.id)
                    jsonObject.put("name", course.name)
                    jsonObject.put("teacher", course.teacher)
                    jsonArray.put(jsonObject)
                }
                file.writeText(jsonArray.toString(4)) // Pretty print with 4 spaces
            }
            "csv" -> {
                file.printWriter().use { out ->
                    out.println("id,name,teacher")
                    data.forEach { course ->
                        out.println("${course.id},${course.name},${course.teacher}")
                    }
                }
            }
            else -> println("Unsupported format")
        }
    }

    fun addCourse(course: Course){
        courseList.forEach{
            courseList.contains(course)
            }
        courseList.add(course)
    //    _state.postValue(courseList)
    }


}