package com.vyatsu.uhtamobile.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.vyatsu.uhtamobile.R
import com.vyatsu.uhtamobile.viewmodels.UhtaViewModel
import com.vyatsu.uhtamobile.ui.theme.UhtaMobileTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AuthView(viewModel: UhtaViewModel, onAuth: () -> Unit) {
    val uiState = viewModel.uiState.collectAsState().value
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    LaunchedEffect(key1 = uiState.employee, block = {
        if (uiState.employee == null) return@LaunchedEffect
        onAuth()
    })
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        topBar = { AuthTopBar() }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = login,
                onValueChange = { login = it.trim() },
                singleLine = true,
                placeholder = { Text(stringResource(R.string.auth_login_placeholder)) })
            TextField(
                value = password,
                onValueChange = { password = it.trim() },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text(stringResource(R.string.auth_password_placeholder)) })
            Button(onClick = {
                coroutineScope.launch(Dispatchers.IO) {
                    viewModel.login(login, password)
                }
            }) {
                Text(stringResource(R.string.auth_login_button))
            }
        }
    }
}

@Composable
private fun AuthTopBar() {
    TopAppBar {
        Spacer(modifier = Modifier.weight(1.0f))
        Text(text = stringResource(R.string.auth_title))
        Spacer(modifier = Modifier.weight(1.0f))
    }
}

@Preview
@Composable
private fun AuthPreview() {
    val uhtaViewModel = UhtaViewModel()
    UhtaMobileTheme {
        AuthView(uhtaViewModel) { }
    }
}