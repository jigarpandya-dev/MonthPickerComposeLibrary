package com.app.monthpickercompose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MonthPickerBottomSheet(
    showYear: Boolean = false,
    title: String = if (showYear) "Select a month and a year" else "Select a month",
    cancelButtonText: String = "Cancel",
    okButtonText: String = "Ok",
    description: String = "",
    onDismiss: () -> Unit,
    onUpdateMonth: (Int, Int) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(onDismissRequest = {
        onDismiss()
    }, sheetState = sheetState) {
        MonthPickerUI(isDialog = false, showYear, title, cancelButtonText, okButtonText, description, onCancel = {
            coroutineScope.launch {
                sheetState.hide()
            }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    onDismiss()
                }
            }
        }, onUpdateMonth = { month, year ->
            coroutineScope.launch {
                sheetState.hide()
            }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    onUpdateMonth(month, year)
                }
            }
        })
    }
}
