package com.vyatsu.uhtamobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vyatsu.uhtamobile.views.AuthView
import com.vyatsu.uhtamobile.viewmodels.UhtaViewModel
import com.vyatsu.uhtamobile.ui.theme.UhtaMobileTheme
import com.vyatsu.uhtamobile.views.DeviceView
import com.vyatsu.uhtamobile.views.EditDeviceView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel: UhtaViewModel = UhtaViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UhtaMobileTheme {
                val coroutineScope = rememberCoroutineScope()
                val context = LocalContext.current
                val state = viewModel.uiState.collectAsState().value
                val navController = rememberNavController()
                LaunchedEffect(Unit) {
                    coroutineScope.launch(Dispatchers.IO) {
                        viewModel.loadDatabase(context)
                    }
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    if (state.isLoaded) {
                        NavHost(navController = navController, startDestination = "auth") {
                            composable("auth") {
                                AuthView(viewModel) {
                                    navController.navigate("devices") {
                                        popUpTo("auth") {
                                            inclusive = true
                                        }
                                    }
                                }
                            }
                            composable("devices") {
                                DeviceView(
                                    viewModel = viewModel,
                                    logout = {
                                        viewModel.logout()
                                        navController.navigate("auth") {
                                            popUpTo("devices") {
                                                inclusive = true
                                            }
                                        }
                                    },
                                    navigateToEdit = { navController.navigate("edit") }
                                )
                            }
                            composable("edit") {
                                EditDeviceView(viewModel = viewModel, exitEdit = {
                                    navController.navigate("devices") {
                                        popUpTo("edit") {
                                            inclusive = true
                                        }
                                    }
                                })
                            }
                        }
                    } else {
                        Text(text = "Загрузка")
                    }
                }
            }
        }
    }
}