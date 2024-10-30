package com.example.readandwritefiles.domain.usecase

import android.content.Context
import com.example.readandwritefiles.domain.model.Course
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class ReadAndWriteFileUseCase {


    val courseList = mutableListOf<Course>()

    fun parseFile(context: Context, fileName: String): MutableList<Course> {
        when (fileName.substringAfterLast(".")) {
            "json" -> parseJson(context, fileName)
            "csv" -> parseCsv(context, fileName)
            else -> {
                println("Unsupported file format")
                //   emptyList()
            }
        }
        return courseList
    }

    fun parseJson(context: Context, fileName: String) {
        courseList.clear()
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getString("id")
            val name = jsonObject.getString("name")
            val teacher = jsonObject.getString("teacher")
            courseList.add(Course(id, name, teacher))
        }
    }

    fun parseCsv(context: Context, fileName: String) {

        courseList.clear()
        context.assets.open(fileName).bufferedReader().useLines { lines ->
            lines.forEach { line ->
                val values = line.split(",")
                val id = values[0]
                val name = values[1]
                val teacher = values[2]
                courseList.add(Course(id, name, teacher))
            }
        }
    }

    fun saveParsedData(data: List<Course>, outputPath: String, format: String): Boolean {
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
                file.writeText(jsonArray.toString(4))
                return true
            }

            "csv" -> {
                file.printWriter().use { out ->
                    out.println("id,name,teacher")
                    data.forEach { course ->
                        out.println("${course.id},${course.name},${course.teacher}")
                    }
                }
                return true
            }

            else -> {
                println("Unsupported format")
                return false
            }
        }
        return false
    }
}