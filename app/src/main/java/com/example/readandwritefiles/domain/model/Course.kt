package com.example.readandwritefiles.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey val id: String,
    val name: String,
    val teacher: String
)
