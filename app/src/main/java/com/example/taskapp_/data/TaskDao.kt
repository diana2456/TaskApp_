package com.example.taskapp_.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task)

    @Query("SELECT* FROM Task")
    fun getTask(): List<Task>

    @Delete
    fun deleteTask(task: Task)

    @Query("SELECT* FROM Task ORDER BY task")
    fun getListByAlphabet():List<Task>
}