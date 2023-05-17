package com.vyatsu.uhtamobile.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.vyatsu.uhtamobile.R
import com.vyatsu.uhtamobile.models.Device
import com.vyatsu.uhtamobile.ui.theme.UhtaMobileTheme
import com.vyatsu.uhtamobile.viewmodels.UhtaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditDeviceView(viewModel: UhtaViewModel, exitEdit: () -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val uiState = viewModel.uiState.collectAsState().value
    val coroutineScope = rememberCoroutineScope()

    @Composable
    fun getTitle(): String {
        return if (uiState.devicesUiState.selectedDevice != null) {
            stringResource(id = R.string.edit_edit_title)
        } else {
            stringResource(id = R.string.edit_create_title)
        }
    }

    val title = remember { mutableStateOf("") }
    val csss = remember { mutableStateOf<Int?>(null) }
    val nr = remember { mutableStateOf<Int?>(null) }
    val unit = remember { mutableStateOf("ШТ") }
    val inOperation = remember { mutableStateOf<Int?>(null) }
    val inStock = remember { mutableStateOf<Int?>(null) }
    fun getDevice(): Device? {
        if (csss.value == null) return null
        if (nr.value == null) return null
        if (uiState.devicesUiState.selectedDevice != null && inOperation.value == null) return null
        if (inStock.value == null) return null
        return Device(
            uiState.devicesUiState.selectedDevice?.id,
            title.value,
            csss.value!!,
            nr.value!!,
            unit.value,
            inOperation.value ?: 0,
            inStock.value!!
        )
    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBar(getTitle()) },
        bottomBar = {
            BottomBar(
                viewModel = viewModel,
                onSaveDevice = {
                    val device = getDevice() ?: return@BottomBar
                    coroutineScope.launch(Dispatchers.IO) {
                        viewModel.saveDevice(device)
                        exitEdit()
                    }
                },
                exitEdit = exitEdit
            )
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = title.value,
                onValueChange = { newValue -> title.value = newValue },
                placeholder = {
                    Text(text = stringResource(id = R.string.device_title_placeholder))
                }
            )
            TextField(
                value = csss.value?.toString() ?: "",
                onValueChange = { newValue ->
                    if (newValue.toIntOrNull() == null) return@TextField
                    if (newValue.isBlank())
                        csss.value = null
                    else
                        csss.value = newValue.toInt()
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = {
                    Text(text = stringResource(id = R.string.device_csss_placeholder))
                }
            )
            TextField(
                value = nr.value?.toString() ?: "",
                onValueChange = { newValue ->
                    if (newValue.toIntOrNull() == null) return@TextField
                    if (newValue.isBlank())
                        nr.value = null
                    else
                        nr.value = newValue.toInt()
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = {
                    Text(text = stringResource(id = R.string.device_nr_placeholder))
                }
            )
            TypeSelector(unit)
            if (uiState.devicesUiState.selectedDevice != null) {
                TextField(
                    value = inOperation.value?.toString() ?: "",
                    onValueChange = { newValue ->
                        if (newValue.toIntOrNull() == null) return@TextField
                        if (newValue.isBlank())
                            inOperation.value = null
                        else
                            inOperation.value = newValue.toInt()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(text = stringResource(id = R.string.device_inoperation_placeholder))
                    }
                )
                TextField(
                    value = inStock.value?.toString() ?: "",
                    onValueChange = { newValue ->
                        if (newValue.toIntOrNull() == null) return@TextField
                        if (newValue.isBlank())
                            inStock.value = null
                        else
                            inStock.value = newValue.toInt()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(text = stringResource(id = R.string.device_instock_placeholder))
                    }
                )
            } else {
                TextField(
                    value = inStock.value?.toString() ?: "",
                    onValueChange = { newValue ->
                        if (newValue.toIntOrNull() == null) return@TextField
                        if (newValue.isBlank())
                            inStock.value = null
                        else
                            inStock.value = newValue.toInt()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(text = stringResource(id = R.string.device_total_placeholder))
                    }
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun TypeSelector(unit: MutableState<String>) {
    val unitTypes = listOf("ШТ", "КМП", "М", "УПК", "КГ", "Т", "М2")
    var expanded by remember { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically) {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            TextField(
                readOnly = true,
                value = unit.value,
                onValueChange = {},
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                }, placeholder = {
                    Text(text = stringResource(id = R.string.device_unit_placeholder))
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                unitTypes.forEach { selectOption ->
                    DropdownMenuItem(onClick = {
                        unit.value = selectOption
                        expanded = false
                    }) {
                        Text(text = selectOption)
                    }
                }
            }
        }
    }
}

@Composable
private fun TopBar(title: String) {
    TopAppBar(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.weight(1.0f))
        Text(text = title)
        Spacer(modifier = Modifier.weight(1.0f))
    }
}

@Composable
private fun BottomBar(viewModel: UhtaViewModel, onSaveDevice: () -> Unit, exitEdit: () -> Unit) {
    val uiState = viewModel.uiState.collectAsState().value

    @Composable
    fun getTitle(): String {
        return if (uiState.devicesUiState.selectedDevice != null) {
            stringResource(id = R.string.edit_save_text)
        } else {
            stringResource(id = R.string.edit_add_text)
        }
    }
    BottomAppBar(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.weight(1.0f))
        Button(onClick = { viewModel.selectDevice(null); exitEdit() }) {
            Text(text = stringResource(id = R.string.edit_cancel_text))
        }
        Spacer(modifier = Modifier.weight(1.0f))
        Button(onClick = { onSaveDevice(); }) {
            Text(text = getTitle())
        }
        Spacer(modifier = Modifier.weight(1.0f))
    }
}

@Preview
@Composable
private fun EditeDevicePreview() {
    val uhtaViewModel = UhtaViewModel(LocalContext.current)
    UhtaMobileTheme {
        EditDeviceView(uhtaViewModel) {}
    }
}