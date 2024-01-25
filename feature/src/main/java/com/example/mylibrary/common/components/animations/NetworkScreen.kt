package com.example.mylibrary.common.components.animations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import com.example.core.network.ConnectionState
import com.example.core.network.currentConnectivityState
import com.example.core.network.observeConnectivityAsFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Compose Estado de conexion
 *
 * @return estado de conexion puede ser [ConnectionState.Available] o [ConnectionState.Unavailable]
 *
 */
@ExperimentalCoroutinesApi
@Composable
fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current

    return produceState(initialValue = context.currentConnectivityState) {
        context.observeConnectivityAsFlow().collect { value = it }
    }
}
