package com.kuloma.testexample.presentation.info

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.kuloma.testexample.ToDoEntity
import com.kuloma.testexample.presentation.theme.TestExampleTheme

class InfoActivity : ComponentActivity() {

    private val viewModel: InfoViewModel by viewModels { InfoViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val entity = ToDoEntity(
            id = intent.extras?.getInt("id"),
            name = intent.extras?.getString("name").toString(),
            description = intent.extras?.getString("description").toString(),
            dateStart = intent.extras?.getString("start_date").toString(),
            dateFinish = intent.extras?.getString("finish_date").toString(),
            dayDate = intent.extras?.getString("day_date").toString()
        )

        setContent {
            TestExampleTheme {
                InfoRoot(
                    activity = this,
                    entity = entity,
                    viewModel = viewModel)
            }
        }
    }
}



