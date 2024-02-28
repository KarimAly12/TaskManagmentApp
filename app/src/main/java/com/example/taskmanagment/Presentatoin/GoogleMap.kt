package com.example.taskmanagment.Presentatoin

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.location.LocationManager
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.location.LocationManagerCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState


@OptIn(ExperimentalPermissionsApi::class)
@Composable

fun LocationScreen(locationViewModel:LocationViewModel){

    val context = LocalContext.current


    val locationPermissionState = rememberMultiplePermissionsState(

        listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )


    LaunchedEffect(locationPermissionState.allPermissionsGranted){
        if(locationPermissionState.allPermissionsGranted){
            if (locationEnabled(context)){
                locationViewModel.getCurrentLocation()


            }else{
                locationViewModel.locationState.value = LocationState.LocationDisabled
            }
        }
    }


    AnimatedContent(locationViewModel.locationState.value) {state->

        when(state){

            is LocationState.NoPermission -> {
                Column {
                    Text("We need location permission to continue")
                    Button(onClick = { locationPermissionState.launchMultiplePermissionRequest() }) {
                        Text("Request permission")
                    }
                }
            }
            is LocationState.LocationDisabled -> {
                Column {
                    Text("We need location to continue")
                    Button(onClick = {requestLocationEnable(locationViewModel, context)}) {
                        Text("Enable location")
                    }
                }
            }
            is LocationState.LocationLoading ->{
                Text("Loading Map")
            }
            is LocationState.Error ->{
                Column {
                    Text("Error fetching your location")
                    Button(onClick = { locationViewModel.getCurrentLocation() }) {
                        Text("Retry")
                    }
                }
            }
            is LocationState.LocationAvailable -> {
                val cameraPositionState = rememberCameraPositionState{
                    position = CameraPosition.fromLatLngZoom(state.location, 15f)
                }

                val mapUIiSettings = remember { mutableStateOf(MapUiSettings()) }
                val mapProperties = remember { mutableStateOf(MapProperties(isMyLocationEnabled = true)) }

                GoogleMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraPositionState = cameraPositionState,
                    uiSettings = mapUIiSettings.value,
                    properties = mapProperties.value
                )


            }

        }

    }

}



fun requestLocationEnable(locationViewModel: LocationViewModel, context: Context){
    context.let {
        val locationRequest = LocationRequest.create()
        val builder = LocationSettingsRequest
            .Builder()
            .addLocationRequest(locationRequest)
        val task = LocationServices
            .getSettingsClient(it)
            .checkLocationSettings(builder.build())
            .addOnSuccessListener {
                if(it.locationSettingsStates?.isLocationPresent == true){
                    locationViewModel.getCurrentLocation()
                }
            }
            .addOnFailureListener{
                if (it is ResolvableApiException){
                    try{
                        it.startResolutionForResult(context as Activity, 999)
                    } catch (e: IntentSender.SendIntentException){
                        e.printStackTrace()
                    }
                }
            }
    }
}



fun locationEnabled(context: Context):Boolean{
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    return LocationManagerCompat.isLocationEnabled(locationManager)

}