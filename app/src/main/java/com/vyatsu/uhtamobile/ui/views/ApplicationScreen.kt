package com.vyatsu.uhtamobile.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.vyatsu.uhtamobile.ui.BottomBar
import com.vyatsu.uhtamobile.ui.UhtaViewModel
import com.vyatsu.uhtamobile.ui.theme.UhtaMobileTheme

@Composable
fun ApplicationView(viewModel: UhtaViewModel, bottomBar: @Composable () -> Unit) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState, bottomBar = bottomBar) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {

        }
    }
}

@Preview
@Composable
private fun ApplicationPreview() {
    val viewModel = UhtaViewModel()
    val navController = rememberNavController()
    UhtaMobileTheme {
        ApplicationView(viewModel) { BottomBar(navController) }
    }
}