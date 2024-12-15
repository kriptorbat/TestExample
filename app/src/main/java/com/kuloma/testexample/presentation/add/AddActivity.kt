package com.kuloma.testexample.presentation.add

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.kuloma.testexample.R
import com.kuloma.testexample.presentation.theme.TestExampleTheme

class AddActivity : ComponentActivity() {

    val viewModel: AddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.veryDarkBlue )
        window.navigationBarColor = ContextCompat.getColor(this, R.color.veryDarkBlue)
        setContent {
            TestExampleTheme{
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