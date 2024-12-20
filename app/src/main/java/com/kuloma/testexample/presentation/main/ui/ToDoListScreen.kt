package com.kuloma.testexample.presentation.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kuloma.testexample.domain.ToDoEntity
import com.kuloma.testexample.presentation.main.MainViewModel
import com.kuloma.testexample.presentation.theme.DarkBlue

@Composable
fun ToDoListScreen(entities: List<ToDoEntity>,
                   toClickItem : (ToDoEntity) -> Unit,
                   viewModel: MainViewModel
) {
    val listState = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = DarkBlue)
    ) {
        LazyColumn(state = listState) {
            items(entities) { entity ->
                ToDoCard(
                    entity = entity,
                    toClickItem = {toClickItem(entity)},
                    viewModel = viewModel
                )
            }
        }
    }
}