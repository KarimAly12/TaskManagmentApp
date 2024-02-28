package com.example.taskmanagment

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskmanagment.Presentatoin.Home.Home


@Composable
fun AppNavigation(navController: NavHostController){

    NavHost(navController = navController, startDestination = "home"){

        composable("home"){

            Home()

        }
    }
    
}