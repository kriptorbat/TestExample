package com.kuloma.testexample.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.asLiveData
import com.kuloma.testexample.ToDoEntity
import com.kuloma.testexample.info.InfoActivity
import com.kuloma.testexample.main.theme.TestExampleTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels {MainViewModel.Factory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            var listToDo by remember {
                mutableStateOf(listOf<ToDoEntity>())
            }
            var datesWithToDo by remember {
                mutableStateOf(setOf<String>())
            }
            viewModel.getAllItem().asLiveData().observe(this){ list ->
                datesWithToDo = list.map { it.dayDate }.toSet()
                for(str:String in datesWithToDo)
                Log.d("dateMainAct",str)
            }
            TestExampleTheme {
                MainRoot(
                    viewModel,
                    onItemClick = { entity ->
                        val intent = Intent(this, InfoActivity::class.java).apply {
                            putExtra("name", entity.name)
                            putExtra("description", entity.description)
                            putExtra("start_date", entity.dateStart)
                            putExtra("finish_date", entity.dateFinish)
                            putExtra("day_date", entity.dayDate)

                        }
                        startActivity(intent)
                    },
                    onDateClick = { day ->
                        viewModel.getAllItemByDate(day).asLiveData().observe(this){ list ->
                            listToDo = list
                        }
                    },
                    toDoList = listToDo,
                    dateWithToDo = datesWithToDo
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TestExampleTheme {
//        val entity = ToDoEntity(
//            id = 1,
//            date_start = "139128338",
//            date_finifsh = "13413251",
//            name = "качаца",
//            description = "Анжумания бегит пресс качат"
//        )
//        ToDoCard(entity)
////        CalendarScreen()
//    }
//}



