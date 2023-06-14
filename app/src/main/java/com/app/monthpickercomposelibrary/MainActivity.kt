package com.app.monthpickercomposelibrary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.monthpickercompose.MonthPickerBottomSheet
import com.app.monthpickercompose.MonthPickerDialog
import com.app.monthpickercomposelibrary.ui.theme.MonthPickerComposeLibraryTheme
import java.text.SimpleDateFormat
import java.util.Calendar

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonthPickerComposeLibraryTheme {
                var openSheet by remember { mutableStateOf(false) }
                var openDialog by remember { mutableStateOf(false) }
                var monthValue by remember { mutableStateOf("") }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(
                            onClick = {
                                openSheet = true
                            },
                            modifier = Modifier
                                .padding(10.dp),
                        ) {
                            Text("Open bottom-sheet")
                        }

                        Button(
                            onClick = {
                                openDialog = true
                            },
                            modifier = Modifier
                                .padding(10.dp),
                        ) {
                            Text("Open dialog")
                        }

                        Text(monthValue, modifier = Modifier.padding(10.dp))
                    }

                    if (openSheet) {
                        MonthPickerBottomSheet(
                            showYear = true,
                            onDismiss = {
                                openSheet = false
                            },
                            onUpdateMonth = { month, year ->
                                val calendar = Calendar.getInstance()
                                calendar.set(Calendar.MONTH, month)
                                calendar.set(Calendar.YEAR, year)

                                openSheet = false

                                monthValue = " ${
                                    SimpleDateFormat("MMMM").format(calendar.time)
                                } - $year"
                            },
                        )
                    }

                    if (openDialog) {
                        MonthPickerDialog(
                            onCancel = { openDialog = false },
                            onUpdateMonth = { month, year ->
                                val calendar = Calendar.getInstance()
                                calendar.set(Calendar.MONTH, month)
                                calendar.set(Calendar.YEAR, year)

                                openDialog = false
                                monthValue = " ${
                                    SimpleDateFormat("MMMM").format(calendar.time)
                                } - $year"
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MonthPickerComposeLibraryTheme {
        Greeting("Android")
    }
}
