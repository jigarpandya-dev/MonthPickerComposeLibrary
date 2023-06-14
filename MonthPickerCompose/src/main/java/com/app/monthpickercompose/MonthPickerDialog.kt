package com.app.monthpickercompose

import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog

@Composable
fun MonthPickerDialog(
    showYear: Boolean = false,
    title: String = if (showYear) "Select a month and a year" else "Select a month",
    cancelButtonText: String = "Cancel",
    okButtonText: String = "Ok",
    description: String = "",
    onCancel: () -> Unit,
    onUpdateMonth: (Int, Int) -> Unit,
) {
    Dialog(onDismissRequest = { onCancel() }) {
        MonthPickerUI(isDialog = true, showYear, title, cancelButtonText, okButtonText, description, onCancel, onUpdateMonth)
    }
}
