package com.example.taskmanagment.Presentatoin.AddTask

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskmanagment.Presentatoin.DatePickerDialog
import com.example.taskmanagment.ui.theme.mainFont
import com.example.taskmanagment.domain.model.Task

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTask(addTaskViewModel: AddTaskViewModel = hiltViewModel()){

    val nameInteractionSource = remember { MutableInteractionSource() }
    val descInteractionSource = remember { MutableInteractionSource() }
    val subtaskInteractionSource = remember { MutableInteractionSource() }


    val isNameFocused = nameInteractionSource.collectIsFocusedAsState()
    val isDescriptionFocused = descInteractionSource.collectIsFocusedAsState()
    val isSubtaskFocused = subtaskInteractionSource.collectIsFocusedAsState()


    val showSubtaskError = remember {
        mutableStateOf(false)
    }

    val showLocationScreen = mutableStateOf(false)

    val showDatePicker = remember {
        mutableStateOf(false)
    }


    if (showDatePicker.value){
        DatePickerDialog(onDateSelected = {
                                          addTaskViewModel.date.value = it
        }, onDismiss = {
            showDatePicker.value = false
        } )

    }


    if(showLocationScreen.value){

        //LocationScreen(locationViewModel = locationViewModel)

    }else{




        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {

            Text(
                text ="Add Task",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp),
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = mainFont,

            )


            //Textfield 1
            TextField(
                label = {

                    Text(
                        text = "Name",
                        color = Color.White,
                        modifier = Modifier
                            .padding(8.dp),
                        fontFamily = mainFont,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(
                            width = 2.dp,
                            color = if (isNameFocused.value) Color(0xffE2FF2D) else Color.Transparent,

                            ),
                        shape = RoundedCornerShape(20.dp)
                    ),

                shape = RoundedCornerShape(20.dp),
                interactionSource = nameInteractionSource,

                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xff303030),
                    unfocusedContainerColor = Color(0xff303030),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    cursorColor = Color.White,
                    unfocusedTextColor = Color.White


                ),

                value = addTaskViewModel.name.value,
                onValueChange = {
                    addTaskViewModel.name.value = it
                })



            //Description
            TextField(
                label = {
                    Text(
                        text = "Description",
                        color = Color.White,
                        modifier = Modifier
                            .padding(8.dp),
                        fontFamily = mainFont,
                        fontWeight = FontWeight.SemiBold
                    )

                },
                value = addTaskViewModel.description.value,
                onValueChange = {
                    addTaskViewModel.description.value = it
                },
                modifier = Modifier
                    .padding(8.dp)
                    .border(
                        width = 2.dp,
                        color = if (isDescriptionFocused.value) Color(0xffE2FF2D) else Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    )

                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                interactionSource = descInteractionSource,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color(0xff303030),
                    unfocusedContainerColor = Color(0xff303030),
                    focusedTextColor = Color.White,
                    cursorColor = Color.White,
                    unfocusedTextColor = Color.White
                )


            )




            //Date
            TextField(
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = {

                        showDatePicker.value = true
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.DateRange,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                label = {
                    Text(
                        text = "Date",
                        color = Color.White,
                        modifier = Modifier
                            .padding(8.dp),
                        fontFamily = mainFont,
                        fontWeight = FontWeight.SemiBold
                    )

                },
                value = addTaskViewModel.date.value,
                onValueChange = {
                    addTaskViewModel.date.value = it
                },
                modifier = Modifier
                    .padding(8.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color(0xff303030),
                    unfocusedContainerColor = Color(0xff303030),
                    focusedTextColor = Color.White,
                    cursorColor = Color.White,
                    unfocusedTextColor = Color.White
                )


            )











            //Subtasks
            Column(
                modifier = Modifier
            ) {




                Row {
                    TextField(
                        label = {
                            Text(
                                text = "Subtasks",
                                color = Color.White,
                                modifier = Modifier
                                    .padding(8.dp),
                                fontFamily = mainFont,
                                fontWeight = FontWeight.SemiBold
                            )
                        },
                        supportingText = {

                            if(showSubtaskError.value){
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = "A subtask with this name exist",
                                    color = MaterialTheme.colorScheme.error
                                )


                            }

                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .border(
                                width = 2.dp,
                                color = if (isSubtaskFocused.value) Color(0xffE2FF2D) else Color.Transparent,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        shape = RoundedCornerShape(20.dp),
                        value = addTaskViewModel.subtaskValue.value,
                        onValueChange = {
                            addTaskViewModel.subtaskValue.value = it
                        },
                        colors = TextFieldDefaults.colors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedContainerColor = Color(0xff303030),
                            unfocusedContainerColor = Color(0xff303030),
                            focusedTextColor = Color.White,
                            cursorColor = Color.White
                        ),

                        interactionSource = subtaskInteractionSource
                    )

                    Button(

                        onClick = {


                            addTaskViewModel.apply {
                                if(subtasks.contains(subtaskValue.value)){
                                    showSubtaskError.value = true
                                }else{
                                    addSubtask()
                                    subtaskValue.value = ""
                                    showSubtaskError.value = false

                                }

                            }


                        },
                        modifier = Modifier

                            .fillMaxWidth()
                            .padding(start = 8.dp, top = 8.dp)
                            .width(39.dp)
                            .height(66.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xff005D92)
                        ),
                        shape = RoundedCornerShape(15.dp)

                    ) {
                        Text(text = "+")
                    }
                }


                subtaskLazyColumn(subtasks = addTaskViewModel.subtasks, addTaskViewModel::onSwipeToDelete)
            }



            Spacer(modifier = Modifier.weight(1f))
            //Add Button
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(229.dp)
                    .height(51.dp),
                onClick = {
                    val task = Task(addTaskViewModel.name.value, addTaskViewModel.description.value, addTaskViewModel.date.value, addTaskViewModel.subtasks)
                    addTaskViewModel.addTask(task)

                },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff005D92)
                )
            ) {
                Text(
                    text = "Add",
                    color = Color.White,
                    fontFamily = mainFont,
                    fontWeight = FontWeight.SemiBold
                )
            }








        }




    }

     // Top column
}