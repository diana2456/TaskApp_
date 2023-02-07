package com.example.taskapp_.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data  class Task(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val task : String,
    val time : Long = System.currentTimeMillis()
)