package com.example.taskmanagment.Presentatoin.Home

import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material.icons.rounded.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.taskmanagment.R

sealed class Screen(val route:String, @StringRes val resourceId:Int, val icon:ImageVector){
    object Home: Screen("home", R.string.homeScreen, Icons.Rounded.Home)
    object AddTaskScreen: Screen("addTask", R.string.addTask, Icons.Rounded.Add)
    object CalenderScreen: Screen("calendar", R.string.calendar, Icons.Rounded.DateRange)
}
