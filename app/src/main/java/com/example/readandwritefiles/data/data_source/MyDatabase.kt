package com.example.readandwritefiles.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.readandwritefiles.domain.model.Course

@Database(
    entities = [Course::class],
    version = 1
)
abstract class MyDatabase(): RoomDatabase() {
    abstract val myDbDao: MyDbDao

    companion object{
        const val DATABASE_NAME = "my_db"
        const val REQUEST_CODE_PICK_FILE = 100
    }
}