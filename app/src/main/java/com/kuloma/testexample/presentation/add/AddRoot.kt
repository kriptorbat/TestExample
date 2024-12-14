package com.kuloma.testexample.presentation.add

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kuloma.testexample.domain.ToDoEntity
import com.kuloma.testexample.presentation.add.ui.AddHeader
import com.kuloma.testexample.presentation.add.ui.TextFieldDescription
import com.kuloma.testexample.presentation.add.ui.TextFieldName
import com.kuloma.testexample.presentation.add.ui.ToDoTimePicker
import com.kuloma.testexample.presentation.theme.VeryDarkBlue

@Composable
fun AddRoot(dayDate: String, onClickBack: () -> Unit,
            onClickAdd: (entity: ToDoEntity) -> Unit,
            viewModel: AddViewModel, context: Context
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
            .padding(10.dp)
        ) {
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

            }, modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = VeryDarkBlue,
                    contentColor = Color.White)
            ) {
                Text(text = "Добавить задачу")
            }
        }
    }
}