package com.vyatsu.uhtamobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vyatsu.uhtamobile.ui.BottomBar
import com.vyatsu.uhtamobile.ui.views.AuthView
import com.vyatsu.uhtamobile.ui.UhtaViewModel
import com.vyatsu.uhtamobile.ui.views.ApplicationView
import com.vyatsu.uhtamobile.ui.theme.UhtaMobileTheme

class MainActivity : ComponentActivity() {
    private val viewModel = UhtaViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController();
            UhtaMobileTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    NavHost(navController = navController, startDestination = "auth"){
                        composable("auth") { AuthView(viewModel) { navController.navigate("application") } }
                        composable("application") { ApplicationView(viewModel) { BottomBar(navController) } }
                    }
                }
            }
        }
    }
}