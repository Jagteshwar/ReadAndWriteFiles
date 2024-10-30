package com.example.readandwritefiles.presentation.dialog

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.readandwritefiles.domain.model.Course
import com.example.readandwritefiles.presentation.viewmodel.AddCourseViewModel
import com.example.readandwritefiles.presentation.viewmodel.ReadWriteFileViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun AlertDialog(
    onDismiss:() ->Unit,
    context: Context,
    courses: List<Course>? = null,
) {
    val fileViewModel: ReadWriteFileViewModel = hiltViewModel()
   // val dbViewModel: AddCourseViewModel = hiltViewModel()

    val dataSaved = fileViewModel.savedData.observeAsState().value

    androidx.compose.material3.AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { /*TODO*/ },
        modifier = Modifier.height(250.dp),

        title = {
            Row(
                horizontalArrangement = Arrangement.Center
            ){
                Text(
                    text = "Choose your format",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        },

        text = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Button(onClick = {

                    courses?.let {
                        fileViewModel.saveParsedData(it, context.filesDir.path + "/output.csv", "csv")
                        if(dataSaved==true){
                            Toast.makeText(context, "data stored in csv format", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, "data FAILED TO store in csv format", Toast.LENGTH_LONG).show()
                        }
                    }

                }) {
                    Text(text = "CSV Format")
                }
                Button(onClick = {
                    courses?.let {
                        fileViewModel.saveParsedData(it, context.filesDir.path + "/output.json", "json")
                        if(dataSaved==true){
                        Toast.makeText(context, "data stored in json format", Toast.LENGTH_SHORT).show()

                        }else{
                        Toast.makeText(context, "data  FAILED TO store in json format", Toast.LENGTH_SHORT).show()
                        }
                    }

                }) {
                    Text(text = "JSON Format")
                }
                Button(onClick = {
                 //   dbViewModel.saveToDB()
                    Toast.makeText(context, "data stored in db", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Save to DB")
                }
            }
        }

        )
}