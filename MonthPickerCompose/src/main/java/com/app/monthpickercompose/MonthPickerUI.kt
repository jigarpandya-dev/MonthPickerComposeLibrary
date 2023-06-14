package com.app.monthpickercompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun MonthPickerUI(
    isDialog: Boolean,
    showYear: Boolean,
    title: String,
    cancelButtonText: String,
    okButtonText: String,
    description: String,
    onCancel: () -> Unit,
    onUpdateMonth: (Int, Int) -> Unit,
) {
    val monthList = listOf(
        "JAN",
        "FEB",
        "MAR",
        "APR",
        "MAY",
        "JUN",
        "JUL",
        "AUG",
        "SEP",
        "OCT",
        "NOV",
        "DEC",
    )
    val currentMonth = rememberSaveable {
        mutableStateOf(0)
    }

    val currentYear = rememberSaveable {
        mutableStateOf(2023)
    }
    Column(
        modifier = Modifier
            .width(400.dp)
            .background(if (isDialog)MaterialTheme.colorScheme.background else Color.Transparent)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(
            text = title.uppercase(),
            modifier = Modifier.padding(vertical = 10.dp),
            fontWeight = (FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary,
        )

        if (description.isNotBlank()) {
            Text(
                text = description,
                modifier = Modifier.padding(vertical = 10.dp),
                color = MaterialTheme.colorScheme.primary,
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_prev),
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        if (currentMonth.value > 0) {
                            currentMonth.value--
                        }
                    },
                contentDescription = "previous month",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary),
            )
            Text(
                modifier = Modifier.width(100.dp),
                textAlign = TextAlign.Center,
                text = monthList[currentMonth.value],
                color = MaterialTheme.colorScheme.primary,
            )
            Image(
                painter = painterResource(id = R.drawable.ic_next),
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        if (currentMonth.value < monthList.size - 1) {
                            currentMonth.value++
                        }
                    },
                contentDescription = "next month",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary),
            )
        }
        if (showYear) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_prev),
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            currentYear.value--
                        },
                    contentDescription = "previous year",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary),
                )
                Text(
                    modifier = Modifier.width(100.dp),
                    textAlign = TextAlign.Center,
                    text = currentYear.value.toString(),
                    color = MaterialTheme.colorScheme.primary,
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_next),
                    modifier = Modifier
                        .padding(10.dp)
                        .clickable {
                            currentYear.value++
                        },
                    contentDescription = "next year",
                    colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.primary),
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(10.dp),
        ) {
            Button(
                onClick = {
                    onCancel()
                },
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
            ) {
                Text(cancelButtonText.uppercase())
            }
            Button(
                onClick = {
                    onUpdateMonth(currentMonth.value, currentYear.value)
                },
                modifier = Modifier
                    .padding(10.dp)
                    .weight(1f),
            ) {
                Text(okButtonText.uppercase())
            }
        }
    }
}
