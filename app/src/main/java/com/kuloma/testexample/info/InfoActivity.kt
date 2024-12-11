package com.kuloma.testexample.info

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuloma.testexample.ToDoEntity
import com.kuloma.testexample.info.ui.theme.TestExampleTheme
import com.kuloma.testexample.main.theme.VeryDarkBlue

class InfoActivity : ComponentActivity() {

    private val viewModel: InfoViewModel by viewModels { InfoViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val entity = ToDoEntity(
            name = intent.extras?.getString("name").toString(),
            description = intent.extras?.getString("description").toString(),
            dateStart = intent.extras?.getString("start_date").toString(),
            dateFinish = intent.extras?.getString("finish_date").toString(),
            dayDate = intent.extras?.getString("day_date").toString()
        )

        setContent {
            TestExampleTheme {
                InfoRoot(
                    entity = entity,
                    viewModel = viewModel)
            }
        }
    }
}

@Composable
fun InfoRoot(entity: ToDoEntity,viewModel: InfoViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(entity.dayDate)
        Column(modifier = Modifier.padding(12.dp)){
            Text(
                fontWeight = FontWeight.Bold,
                text = entity.name
            )
            Text(text = entity.description)
            Text(text = "Время начала ${viewModel.formatTimestamp(entity.dateStart.toLong())}") //viewModel.formatToStamp(entity.dateStart)
            Text(text = "Время конца ${viewModel.formatTimestamp(entity.dateFinish.toLong())}") //viewModel.formatToStamp(entity.dateStart)
        }
    }
}

@Composable
fun Header(dayDate: String){
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(VeryDarkBlue)
            .padding(20.dp),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        text =  "Список дел на ${dayDate}",
        color = Color.White

    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val entity = ToDoEntity(
        name = "Помыть посуду",
        description = "Вымыть 4 тарелки и 4 кружки с 4 вилками",
        dateStart = "12:40",
        dateFinish = "13:40",
        dayDate = "2024.12.11"
    )
    TestExampleTheme {
        //InfoRoot(entity)
    }
}