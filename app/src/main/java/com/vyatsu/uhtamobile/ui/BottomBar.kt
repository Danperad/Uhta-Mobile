package com.vyatsu.uhtamobile.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.vyatsu.uhtamobile.R

@Composable
fun BottomBar(navController: NavController) {
    BottomAppBar(modifier = Modifier.fillMaxWidth()) {
        IconButton(onClick = { navController.navigate("application") }, modifier = Modifier.weight(1.0f)) {
            Icon(Icons.Default.List, contentDescription = stringResource(R.string.bottombar_application_description))
        }
        IconButton(onClick = { navController.navigate("devices") }, modifier = Modifier.weight(1.0f)) {
            Icon(Icons.Default.Done, contentDescription = stringResource(R.string.bottombar_devices_description))
        }
        IconButton(onClick = { navController.navigate("report") }, modifier = Modifier.weight(1.0f)) {
            Icon(Icons.Default.List, contentDescription = stringResource(R.string.bottombar_report_description))
        }
        IconButton(onClick = { navController.navigate("archive") }, modifier = Modifier.weight(1.0f)) {
            Icon(Icons.Default.List, contentDescription = stringResource(R.string.bottombar_archive_description))
        }
    }
}