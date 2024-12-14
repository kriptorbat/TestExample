package com.kuloma.testexample.presentation.add.ui

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kuloma.testexample.presentation.theme.Blue
import com.kuloma.testexample.presentation.theme.DarkBlue
import com.kuloma.testexample.presentation.theme.VeryDarkBlue

@Composable
fun TextFieldName(onTextChange: (String) -> Unit) {
    val maxChar = 30
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
                focusedContainerColor = DarkBlue,
                unfocusedContainerColor = VeryDarkBlue,
                cursorColor = Blue,
                focusedIndicatorColor = Blue
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
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