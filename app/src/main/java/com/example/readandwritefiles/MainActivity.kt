package com.example.readandwritefiles

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.readandwritefiles.data.data_source.MyDatabase.Companion.REQUEST_CODE_PICK_FILE
import com.example.readandwritefiles.domain.usecase.InsertCourseUseCase
import com.example.readandwritefiles.presentation.screens.FileScreen
import com.example.readandwritefiles.presentation.viewmodel.AddCourseViewModel
import com.example.readandwritefiles.presentation.viewmodel.ReadWriteFileViewModel
import com.example.readandwritefiles.ui.theme.ReadAndWriteFilesTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
           // val viewModel: AddCourseViewModel by viewModels { AddCourseViewModel() }
        //    val addToDbViewModel by viewModels<AddCourseViewModel>()
             val viewModel: AddCourseViewModel by viewModels()
            val fileViewModel: ReadWriteFileViewModel by viewModels()

            ReadAndWriteFilesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FileScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
