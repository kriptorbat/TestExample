package com.kuloma.testexample.presentation.add.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kuloma.testexample.presentation.theme.Blue
import com.kuloma.testexample.presentation.theme.VeryDarkBlue

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