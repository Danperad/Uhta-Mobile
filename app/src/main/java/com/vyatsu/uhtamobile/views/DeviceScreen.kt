package com.vyatsu.uhtamobile.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.vyatsu.uhtamobile.R
import com.vyatsu.uhtamobile.ui.theme.UhtaMobileTheme
import com.vyatsu.uhtamobile.viewmodels.UhtaViewModel

@Composable
fun DeviceView(viewModel: UhtaViewModel, logout: () -> Unit, navigateToEdit: () -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val uiState = viewModel.uiState.collectAsState().value
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = { TopBar(logout) },
        floatingActionButton = { AddButton(viewModel = viewModel,navigateToCreate = navigateToEdit)}
    ) {
        Column(modifier = Modifier.padding(it)) {
            uiState.devicesUiState.devices.forEach {
                Row(modifier = Modifier.clickable { viewModel.selectDevice(it); navigateToEdit() }) {
                   Text(it.title)
                }
            }
        }
    }
}

@Composable
private fun TopBar(logout: () -> Unit) {
    TopAppBar(modifier = Modifier.fillMaxWidth()) {
        Text(text = stringResource(id = R.string.device_topbar_title))
        Spacer(modifier = Modifier.weight(1.0f))
        IconButton(onClick = { logout() }) {
            Icon(
                imageVector = Icons.Default.ExitToApp, contentDescription = stringResource(
                    id = R.string.exit_button_description
                )
            )
        }
    }
}

@Composable
private fun AddButton(viewModel: UhtaViewModel, navigateToCreate: () -> Unit) {
    FloatingActionButton(onClick = {viewModel.selectDevice(null) ;navigateToCreate() }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(id = R.string.device_create_description)
        )
    }
}

@Preview
@Composable
private fun DevicePreview() {
    val viewModel = UhtaViewModel()
    UhtaMobileTheme {
        DeviceView(viewModel, logout = {}, navigateToEdit = {})
    }
}