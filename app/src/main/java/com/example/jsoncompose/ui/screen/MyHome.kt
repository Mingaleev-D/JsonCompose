package com.example.jsoncompose.ui.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jsoncompose.ui.components.Questions
import com.example.jsoncompose.ui.viewmodel.QuestionsViewModel

/**
 * @author : Mingaleev D
 * @data : 14.05.2023
 */

@Composable
fun MyHome(viewModel: QuestionsViewModel = hiltViewModel()) {
   Questions(viewModel = viewModel)
}