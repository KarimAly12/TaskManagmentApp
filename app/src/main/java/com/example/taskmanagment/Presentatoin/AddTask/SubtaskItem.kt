package com.example.taskmanagment.Presentatoin.AddTask

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.taskmanagment.ui.theme.mainFont


@Composable
fun subtaskItem(subtask:String){

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(
                color = Color(0xff303030),
                shape = RoundedCornerShape(10.dp)
            )
            .padding(8.dp)
    ) {

        Text(
            text = subtask,
            color = Color.White,
            fontFamily = mainFont,
            fontWeight = FontWeight.Normal
        )
    }
}