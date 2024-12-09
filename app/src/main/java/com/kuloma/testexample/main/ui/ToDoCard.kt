package com.kuloma.testexample.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuloma.testexample.ToDoEntity
import com.kuloma.testexample.main.MainViewModel
import com.kuloma.testexample.ui.theme.Blue
import com.kuloma.testexample.ui.theme.VeryDarkBlue
import java.sql.Timestamp

@Composable
fun ToDoCard(entity: ToDoEntity, toClickItem : () -> Unit, viewModel: MainViewModel) {
    val startTime = viewModel.formatTimestamp(entity.dateStart.toLong())
    val endTime = viewModel.formatTimestamp(entity.dateFinish.toLong())

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(
                top = 8.dp,
                start = 4.dp,
                end = 4.dp
            )
            .clickable { toClickItem() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = VeryDarkBlue)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .width(10.dp) // Ширина линии
                    .fillMaxHeight() // Заполнить высоту
                    .background(Blue) // Цвет линии
            )
            Text(
                text = entity.name,
                fontSize = 20.sp,
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .width(1.dp) // Ширина линии
                        .fillMaxHeight() // Заполнить высоту
                        .background(Color.White) // Цвет линии
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Column(
                    modifier = Modifier.padding(end = 10.dp)
                ) {
                    Text(
                        text = startTime,
                        fontSize = 16.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                    Text(
                        text = endTime,
                        fontSize = 16.sp,
                        color = Color.White,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}