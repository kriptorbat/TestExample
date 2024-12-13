package com.kuloma.testexample.presentation.info

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kuloma.testexample.domain.ToDoEntity
import com.kuloma.testexample.presentation.info.ui.Header

@Composable
fun InfoRoot(activity: ComponentActivity, entity: ToDoEntity, viewModel: InfoViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(
            entity.dayDate,
            onClickDelete = {
                activity.setResult(entity.id!!)
                activity.finish()
            },
            onClickBack = {
                activity.finish()
            })
        Column(modifier = Modifier.padding(12.dp)){
            Text(
                fontWeight = FontWeight.Bold,
                text = entity.name
            )
            Text(text = entity.description)
            Text(text = "Время начала ${viewModel.formatTimestamp(entity.dateStart.toLong())}")
            Text(text = "Время конца ${viewModel.formatTimestamp(entity.dateFinish.toLong())}")
        }
    }
}