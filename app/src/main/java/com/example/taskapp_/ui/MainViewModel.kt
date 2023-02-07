package com.example.taskapp_.ui

import android.widget.Adapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskapp_.data.Task
import com.example.taskapp_.data.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val dao:TaskDao): ViewModel() {
    private val event = MutableLiveData<String>()
    val  adapter =  MainAdapter(this::click)

    val tasks = MutableLiveData<List<Task>>().apply {
        value = ArrayList()
    }

    fun insertTask(task: String) {
       if (task.isNotEmpty()&& task.isNotBlank())
            dao.insert(Task(task = task))
        else event.value = "Task is empty"
    }

    fun getAllTask(){
       tasks.value = dao.getTask()
    }

    private fun click(pos:Int){
        dao.deleteTask(adapter.getTask(pos))
        setData()
    }

    private fun setData() {
        val listOfTask = dao.getTask()
        adapter.updateList(listOfTask)
    }

    fun sort(){
        adapter.updateList(dao.getListByAlphabet())
    }

}

