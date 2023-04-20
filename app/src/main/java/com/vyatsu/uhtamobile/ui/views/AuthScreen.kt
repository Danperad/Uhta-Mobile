package com.vyatsu.uhtamobile.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.vyatsu.uhtamobile.R
import com.vyatsu.uhtamobile.ui.UhtaViewModel
import com.vyatsu.uhtamobile.ui.theme.UhtaMobileTheme


@Composable
fun AuthView(viewModel: UhtaViewModel, onAuth: () -> Unit) {
    val scaffoldState = rememberScaffoldState()
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { AuthTopBar() },
        bottomBar = { AuthBottomBar() }) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = login,
                onValueChange = { login = it },
                singleLine = true,
                placeholder = { Text(stringResource(R.string.auth_login_placeholder)) })
            TextField(
                value = password,
                onValueChange = { password = it },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text(stringResource(R.string.auth_password_placeholder)) })
            Button(onClick = {
                if (viewModel.auth(login, password)) {
                    onAuth()
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

@Composable
private fun AuthBottomBar() {
    BottomAppBar { }
}

@Preview
@Composable
private fun AuthPreview() {
    val uhtaViewModel = UhtaViewModel()
    UhtaMobileTheme {
        AuthView(uhtaViewModel) { }
    }
}