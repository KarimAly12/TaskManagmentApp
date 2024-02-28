package com.example.taskmanagment.Presentatoin

import android.app.DatePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialog(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
){
    val datePickerState = rememberDatePickerState(selectableDates = object : SelectableDates {
        override fun isSelectableDate(utcTimeMillis: Long): Boolean {
            return true
        }
    })

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    
    
    DatePickerDialog(
        shape = RoundedCornerShape(20.dp),
        colors = DatePickerDefaults.colors(
            containerColor = Color(0xff303030)
        ),
        onDismissRequest = { onDismiss() },
        confirmButton = { 
            Button(
                 colors = ButtonDefaults.buttonColors(
                     containerColor = Color(0xff005D92)
                 ),
                onClick = {
                onDateSelected(selectedDate)
                onDismiss()
            }) {

                Text(text = "Ok")
            }
            
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff005D92)
                ),
                onClick = {
                onDismiss()
            }) {
                Text(text = "Cancel")
            }
        }

    ) {

        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                dayContentColor = Color.White,
                weekdayContentColor = Color.White,
                titleContentColor = Color.White,
                navigationContentColor = Color.White,
                selectedDayContainerColor = Color(0xff005D92),
                todayContentColor = Color.White,
                todayDateBorderColor = Color.White,
                selectedYearContainerColor = Color(0xff005D92),
                selectedDayContentColor = Color.White

            )
        )
    }
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}