package com.kuloma.testexample.presentation.add.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@SuppressLint("DefaultLocale")
@Composable
fun TimeText(hour: Int, minute: Int,onClickTime : () -> Unit){
    Text(
        text = "${hour}:${
            String.format(
                "%02d",
                minute
            )
        }",
        modifier = Modifier.clickable(onClick = {
            onClickTime()
        }),
        fontSize = 40.sp,
        color = Color.White,
        textDecoration = TextDecoration.Underline
    )
}