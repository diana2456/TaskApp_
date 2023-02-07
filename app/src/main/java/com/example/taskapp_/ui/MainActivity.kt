package com.example.taskapp_.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import com.example.taskapp_.R
import com.example.taskapp_.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initialize()
        listener()
        observers()
        setContentView(binding.root)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.sort ->{
                vm.sort()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    private fun initialize() {
        binding.todoRv.adapter = vm.adapter
        vm.getAllTask()
    }

    private fun observers() {
        vm.tasks.observe(this){
            binding.todoSwipe.isRefreshing = false
            vm.adapter.updateList(it)
        }
        vm.tasks.observe(this){
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
        }
    }

    private fun listener() {
        binding.todoBtn.setOnClickListener {
            vm.insertTask(binding.todoEdt.text.toString())
        }

        binding.todoSwipe.setOnRefreshListener {
            vm.getAllTask()
        }
    }
}