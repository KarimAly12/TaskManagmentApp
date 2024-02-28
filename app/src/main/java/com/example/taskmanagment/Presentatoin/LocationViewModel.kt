package com.example.taskmanagment.Presentatoin

import android.annotation.SuppressLint
import android.location.Location
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(




):ViewModel() {
    lateinit var fusedLocationClient: FusedLocationProviderClient
    //lateinit var task: Task<Location>

    var locationState = mutableStateOf<LocationState>(LocationState.NoPermission)

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(){
        locationState.value = LocationState.LocationLoading
        fusedLocationClient.lastLocation.addOnSuccessListener {location ->
            locationState.value = if (location == null && locationState.value !is LocationState.LocationAvailable){
                LocationState.Error
            }else{
                LocationState.LocationAvailable(LatLng(location.latitude, location.longitude))
            }

        }



    }
}