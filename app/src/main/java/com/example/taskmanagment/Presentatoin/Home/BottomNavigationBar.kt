package com.example.taskmanagment.Presentatoin.Home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.taskmanagment.domain.usecase.AddTask
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.taskmanagment.Presentatoin.AddTask.AddTask

@Composable
fun BottomNavigation(){

    val items = remember{
        mutableListOf(
        Screen.Home,
        Screen.AddTaskScreen,
        Screen.CalenderScreen
        )
    }

    val navController = rememberNavController()

    Scaffold(
        backgroundColor = Color(0xff1C1A1A),
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color(0xff303030),
                contentColor = Color.White
            ) {
                val navBackStackEntry = navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry.value?.destination

                items.forEach{screen ->  
                    
                    BottomNavigationItem(
                        selected = currentDestination?.hierarchy?.any{it.route == screen.route} == true,
                        onClick = {

                              navController.navigate(screen.route){
                                  popUpTo(navController.graph.findStartDestination().id){
                                      saveState = true
                                  }

                                  launchSingleTop = true
                                  restoreState = true

                              }

                        },
                        icon = { Icon(
                            screen.icon,
                            contentDescription = null,
                            ) },
                        label = { Text(text = stringResource(id = screen.resourceId)) },
                        selectedContentColor = Color(0xffE2FF2D),
                        unselectedContentColor = Color.White
                    )

                }
            }



        }
    ) {innerPadding ->
        NavHost(navController = navController, startDestination = Screen.Home.route, Modifier.padding(innerPadding) ){
            composable(Screen.Home.route){
                Home()
            }
            composable(Screen.AddTaskScreen.route){
                AddTask()
            }

            composable(Screen.CalenderScreen.route){

            }
        }

    }




}