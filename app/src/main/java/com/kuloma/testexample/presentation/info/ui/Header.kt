package com.kuloma.testexample.presentation.info.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kuloma.testexample.presentation.theme.VeryDarkBlue

@Composable
fun Header(dayDate: String,onClickDelete: () -> Unit, onClickBack: () -> Unit){
    Row (modifier = Modifier
        .fillMaxWidth()
        .background(VeryDarkBlue)
        .padding(start = 20.dp, end = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
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
            text =  dayDate,
            color = Color.White

        )
        IconButton(onClick = {
            onClickDelete()
        }
        ) {
            Icon(
                Icons.Filled.Delete,
                contentDescription = "delete",
                tint = Color.White)
        }
    }
}