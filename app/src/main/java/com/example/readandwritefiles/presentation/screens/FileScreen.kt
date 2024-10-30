package com.example.readandwritefiles.presentation.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.readandwritefiles.data.data_source.MyDatabase.Companion.REQUEST_CODE_PICK_FILE
import com.example.readandwritefiles.domain.model.Course
import com.example.readandwritefiles.presentation.dialog.AlertDialog
import com.example.readandwritefiles.presentation.viewmodel.ReadWriteFileViewModel
import java.io.File

@Composable
fun FileScreen(
    modifier: Modifier = Modifier,
    fileViewModel: ReadWriteFileViewModel = hiltViewModel()
) {

    var showDialog by remember {
        mutableStateOf(false)
    }


    var filename by remember {
        mutableStateOf("")
    }

    var fileData by remember {
        mutableStateOf("")
    }

    val result = remember {
        mutableStateOf<Uri?>( null)
    }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = {
                result.value = it
        })

    var id by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var teacher by remember { mutableStateOf("") }

    val context = LocalContext.current
    val courses = fileViewModel.state.observeAsState().value
    if (showDialog) {
        AlertDialog(onDismiss = { showDialog = false }, context, courses)
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {



        OutlinedTextField(
            value = filename,
            onValueChange = {
                filename = it
            },
            label = {
                Text(text = "Enter file name.")
            },
            singleLine = true
        )




        Text(modifier = Modifier.padding(top = 30.dp), text = "File Data", fontSize = 26.sp)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            if (courses != null) {
                items(courses) {
                    RowItem(it = it)
                }
            }
        }


//        LazyRow(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceEvenly,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            items(1) {
//                OutlinedTextField(
//                    value = id,
//                    onValueChange = { id = it },
//                    label = { Text(text = "Id") },
//                    singleLine = true
//                )
//                OutlinedTextField(
//                    value = name,
//                    onValueChange = { name = it },
//                    label = { Text(text = "course") },
//                    singleLine = true
//                )
//                OutlinedTextField(
//                    value = teacher,
//                    onValueChange = { teacher = it },
//                    label = { Text(text = "teacher") },
//                    singleLine = true
//                )
//
//            }
//        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp, top = 20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                showDialog = true
                // writeData(fileData, fileOutputStream)
            }) {
                Text(text = "Save")
            }
            Button(onClick = {
                fileViewModel.addCourse(
                    Course(
                        id = id,
                        name = name,
                        teacher = teacher
                    )
                )
                // writeData(fileData, fileOutputStream)
            }) {
                Text(text = "Add")
            }
            Button(onClick = {
                // viewData(context, courses)
                fileViewModel.viewData(context, filename)
                Log.i("CsvReader", "FileSize: ${fileViewModel.listSizeState.value}")
            }) {
                Text(text = "View")
            }
            Button(onClick = {
                fileViewModel.removeList()
            }) {
                Text(text = "Clear")
            }
        }


    }
}

@Composable
fun RowItem(it: Course) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = it.id)
        Text(text = it.name)
        Text(text = it.teacher)
    }
}


