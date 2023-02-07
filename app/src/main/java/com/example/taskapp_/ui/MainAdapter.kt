package com.example.taskapp_.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp_.data.Task
import com.example.taskapp_.databinding.ItemTaskBinding
import com.example.taskapp_.extesion.convertTime


class MainAdapter (private var onClick:(Int)-> Unit):RecyclerView.Adapter<MainAdapter.ViewHolder>(){


    fun getTask(pos:Int):Task{
        return tasks[pos]
    }

    private var tasks = arrayListOf<Task>()
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(tasks:List<Task>){
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()

    }

    inner class ViewHolder(private val binding : ItemTaskBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(task: Task) {
            binding.itemTv.text = task.task
            binding.itemData.text = task.time.convertTime()

           itemView.setOnClickListener {
               onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(tasks[position])
    }
}