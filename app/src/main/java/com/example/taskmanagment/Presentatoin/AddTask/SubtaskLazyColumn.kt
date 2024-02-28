package com.example.taskmanagment.Presentatoin.AddTask

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun subtaskLazyColumn(
    subtasks: MutableList<String>,
    onSwipeToDelete: (String) -> Unit
){

    LazyColumn{
        items(subtasks){




            val delete = SwipeAction(
                onSwipe = {

                    onSwipeToDelete(it)

                },
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = "Delete chat",
                        modifier = Modifier.padding(16.dp),
                        tint = Color.White
                    )
                },
                background = Color(0xffE2FF2D).copy(alpha = 0.5f),
                isUndo = true
            )

            SwipeableActionsBox(
                modifier = Modifier,
                swipeThreshold = 200.dp,
                endActions = listOf(delete)
            ) {

                subtaskItem(subtask = it)
            }

            //subtaskItem(item)
        }
    }
}