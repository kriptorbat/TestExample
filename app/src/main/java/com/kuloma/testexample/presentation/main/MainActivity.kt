package com.kuloma.testexample.presentation.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.distinctUntilChanged
import com.kuloma.testexample.R
import com.kuloma.testexample.domain.ToDoEntity
import com.kuloma.testexample.presentation.add.AddActivity
import com.kuloma.testexample.presentation.info.InfoActivity
import com.kuloma.testexample.presentation.theme.TestExampleTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.veryDarkBlue)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.veryDarkBlue)
        setContent {
            var changedDate by remember {
                mutableStateOf("")
            }
            var listToDo by remember {
                mutableStateOf(listOf<ToDoEntity>())
            }
            var allListToDo by remember {
                mutableStateOf(listOf<ToDoEntity>())
            }
            var datesWithToDo by remember {
                mutableStateOf(setOf<String>())
            }
            val infoLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    Log.d("result", result.resultCode.toString())
                    viewModel.deleteToDo(result.resultCode)
                }
            val addLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    Log.d("result", result.resultCode.toString())
                    result.data?.let {
                        val entity = ToDoEntity(
                            dateStart = it.getStringExtra("date_start").toString(),
                            dateFinish = it.getStringExtra("date_finish").toString(),
                            name = it.getStringExtra("name").toString(),
                            description = it.getStringExtra("description").toString(),
                            dayDate = it.getStringExtra("dayDate").toString()
                        )
                        viewModel.addItem(entity)
                    }
                }


            viewModel.getAllItem().distinctUntilChanged().observe(this@MainActivity) { list ->
                datesWithToDo = list.map { it.dayDate }.toSet()
                allListToDo = list
                listToDo = list.filter { it.dayDate == changedDate }

                Log.d("tag", "observeGetAllItems")
            }


            TestExampleTheme {
                MainRoot(
                    viewModel,
                    onItemClick = { entity ->
                        val intent = Intent(this, InfoActivity::class.java).apply {
                            putExtra("id", entity.id)
                            putExtra("name", entity.name)
                            putExtra("description", entity.description)
                            putExtra("start_date", entity.dateStart)
                            putExtra("finish_date", entity.dateFinish)
                            putExtra("day_date", entity.dayDate)
                        }
                        infoLauncher.launch(intent)
                    },
                    onDateClick = { day ->
                        changedDate = day
                        listToDo = allListToDo.filter { it.dayDate == changedDate }

                    },
                    onClickAdd = {
                        if (changedDate.isNotEmpty()) {
                            val intent = Intent(this, AddActivity::class.java).apply {
                                putExtra("day_date", changedDate)
                            }

                            addLauncher.launch(intent)
                        } else Toast.makeText(this, "Выбирите дату в календаре", Toast.LENGTH_SHORT)
                            .show()
                    },
                    toDoList = listToDo,
                    dateWithToDo = datesWithToDo
                )
            }
        }
    }
}



