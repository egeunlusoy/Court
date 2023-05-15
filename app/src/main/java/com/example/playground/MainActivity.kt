package com.example.playground

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Chip
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.playground.ui.theme.PlaygroundTheme
import java.util.Calendar



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlaygroundTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}


@Composable
fun MainScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            textAlign = TextAlign.Center,
                            text= "Search",
                            modifier = Modifier.fillMaxWidth(),

                        )
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black,


            )
        },
        content = {  innerPadding ->
                Row( modifier = Modifier.padding(horizontal= 6.dp , vertical=10.dp)

                )
                {
                    SportSearch()
                    DateButton()
                }


            }

    )
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowCalendar(fragmentManager: FragmentManager){
    val dp =
        MaterialDatePicker
        .Builder
        .datePicker()
        .setTitleText("Select date")
        .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
        .build()

   // dp.show(context,"Date_pick")
}*/

@OptIn(ExperimentalMaterialApi::class)
@Composable
    fun DateButton(){
    val c = Calendar.getInstance()
    c.firstDayOfWeek = Calendar.MONDAY
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)


    val context = LocalContext.current
    var date by remember {
        mutableStateOf("")
    }

    val datePickerDialog = DatePickerDialog(
        context,{d, year1, month1, day1 ->
            val month= month1 + 1
            date = "$day1 - $month1 - $year1"
        },year,month,day
    )

    Chip(
        onClick = { datePickerDialog.show()
                   },
        border = BorderStroke(
            ChipDefaults.OutlinedBorderSize,
            Color.Black
        ),
        colors = ChipDefaults.chipColors(
            backgroundColor = Color.White,
            contentColor = Color.Black
        ),
        leadingIcon = {
            Icon(

                Icons.Outlined.DateRange,
                contentDescription = "Calendar"
            )
        },
    ) {
        Text("Set Date")
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SportSearch() {

    val options = listOf("football", "basketball")
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
        ) {
            TextField(
                readOnly = true,
                value = selectedOptionText,
                onValueChange = { },
                textStyle = TextStyle.Default.copy(
                    fontSize = 15.sp,
                    ),
                label = {
                    Text(
                        text = "Choose the sport",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                },

                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    backgroundColor = Color.White,
                    trailingIconColor = Color.Black,
                    focusedTrailingIconColor = Color.Black,
                    leadingIconColor = Color.Black,
                    focusedLabelColor = Color.Black,
                )
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        onClick = {
                            selectedOptionText = selectionOption
                            expanded = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = selectionOption)
                    }
                }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PlaygroundTheme {
       // Greeting("Android")
    }
}