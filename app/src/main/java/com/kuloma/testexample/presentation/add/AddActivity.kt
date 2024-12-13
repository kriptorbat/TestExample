package com.kuloma.testexample.presentation.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuloma.testexample.ToDoEntity
import com.kuloma.testexample.presentation.info.ui.Header
import com.kuloma.testexample.presentation.theme.Blue
import com.kuloma.testexample.presentation.theme.TestExampleTheme
import com.kuloma.testexample.presentation.theme.VeryDarkBlue
import java.time.LocalTime
import java.util.Calendar

class AddActivity : ComponentActivity() {

    val viewModel: AddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestExampleTheme {
                AddRoot(
                    dayDate = intent.extras?.getString("day_date").toString(),
                    onClickBack = {
                        finish()
                    },
                    onClickAdd = { entity ->
                        val resultIntent = Intent().apply {
                            putExtra("date_start", entity.dateStart)
                            putExtra("date_finish", entity.dateFinish)
                            putExtra("name", entity.name)
                            putExtra("description", entity.description)
                            putExtra("dayDate", entity.dayDate)
                        }

                        setResult(Activity.RESULT_OK, resultIntent)
                        finish()
                        Log.d("entity",entity.toString())
                    },
                    viewModel = viewModel,
                    context = this
                )
            }
        }
    }
}

@Composable
fun AddRoot(dayDate: String,onClickBack: () -> Unit,
            onClickAdd: (entity: ToDoEntity) -> Unit,
            viewModel: AddViewModel,context: Context
) {
    var name by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var startTime by remember {
        mutableStateOf("")
    }
    var endTime by remember {
        mutableStateOf("")
    }
    Column {
        AddHeader(dayDate = dayDate, onClickBack = {onClickBack()})
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            TextFieldName(onTextChange = { textName -> name = textName })
            TextFieldDescription(onTextChange =  { textDescription -> description = textDescription })
            ToDoTimePicker(dayDate,viewModel,
                onTimeSelected = {startTimeValue, endTimeValue ->
                startTime = startTimeValue
                endTime = endTimeValue
            })
            Button(onClick = {
                if(name.isNotEmpty() && description.isNotEmpty()){
                    val entity = ToDoEntity(
                        name = name,
                        description = description,
                        dateStart = startTime,
                        dateFinish = endTime,
                        dayDate = dayDate
                    )
                    onClickAdd(entity)
                } else Toast.makeText(context,
                    "Не введены данные в название или в описание",
                    Toast.LENGTH_SHORT).show()

            }) {
                Text(text = "Добавить задачу")
            }
        }
    }
}

@Composable
fun AddHeader(dayDate: String, onClickBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(VeryDarkBlue)
            .padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {
            onClickBack()
        }) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "back",
                tint = Color.White
            )
        }
        Text(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            text = dayDate,
            color = Color.White
        )
    }
}

@Composable
fun TextFieldName(onTextChange: (String) -> Unit) {
    val maxChar = 15
    var text by remember {
        mutableStateOf("")
    }
    Column {
        Text(
            text = "Название",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            color = VeryDarkBlue
        )

        TextField(
            value = text,
            onValueChange = {
                if (it.length <= maxChar){
                    text = it
                    onTextChange(it)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Blue,
                unfocusedContainerColor = VeryDarkBlue
            )
        )
        Text(
            text = "${text.length} / $maxChar",
            textAlign = TextAlign.End,
            color = VeryDarkBlue,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun TextFieldDescription(onTextChange: (String) -> Unit) {
    val maxChar = 200
    var text by remember {
        mutableStateOf("")
    }
    Column {
        Text(
            text = "Описание",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            color = VeryDarkBlue
        )

        TextField(
            value = text,
            onValueChange = {
                if (it.length <= maxChar) {
                    onTextChange(it)
                    text = it
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
                .heightIn(min = 120.dp),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Blue,
                unfocusedContainerColor = VeryDarkBlue
            ),
            singleLine = false,
            maxLines = 5
        )
        Text(
            text = "${text.length} / $maxChar",
            textAlign = TextAlign.End,
            color = VeryDarkBlue,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.fillMaxWidth()
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoTimePicker(dayDate: String,viewModel: AddViewModel,onTimeSelected: (String,String) -> Unit){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        val currentTime = Calendar.getInstance()

        val startTimePickerState =
            rememberTimePickerState(
                initialHour = currentTime[Calendar.HOUR_OF_DAY],
                initialMinute = currentTime[Calendar.MINUTE],
                is24Hour = true
            )
        val endTimePickerState =
            rememberTimePickerState(
                initialHour = currentTime[Calendar.HOUR_OF_DAY],
                initialMinute = currentTime[Calendar.MINUTE],
                is24Hour = true
            )

        Card {
            Column (
                modifier = Modifier.fillMaxWidth()
                    .background(color = VeryDarkBlue),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text = "Время начала ", color = Color.White)
                 TimeInput(
                    state = startTimePickerState
                )
                Text(text = "Время конца ", color = Color.White)
                TimeInput(
                    state = endTimePickerState
                )
                LaunchedEffect(startTimePickerState.hour, startTimePickerState.minute, endTimePickerState.hour, endTimePickerState.minute) {
                    val startTimestamp = viewModel.getTimestamp(dayDate, startTimePickerState.hour, startTimePickerState.minute)
                    val endTimestamp = viewModel.getTimestamp(dayDate, endTimePickerState.hour, endTimePickerState.minute)
                    Log.d("writeData","minute = ${endTimePickerState.minute}")
                    onTimeSelected(startTimestamp, endTimestamp)
                }
            }
        }
    }
}





@Preview(showBackground = true)
@Composable
fun Preview() {
    TestExampleTheme {
//        AddRoot(onClickBack = {}, onClickAdd = {}, dayDate = "24.12.22")
//        ToDoTimePicker()
    }
}