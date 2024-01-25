package com.example.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.text.DecimalFormat

class Util {
    companion object {
        val PATTERN = "#,###"
        val OLD_VALUE = ","
        val NEW_VALUE = "."
        val CONT_HTTP = "http://"
        val CONT_HTTPS = "https://"
    }
}

/**
 * Obtener estado de la conexion a la red
 *
 * @return ConnectionState puede ser [ConnectionState.Available] o [ConnectionState.Unavailable]
 *
 */
val Context.currentConnectivityState: ConnectionState
    get() {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return getCurrentConnectivityState(connectivityManager)
    }

/**
 * Obtener estado de la conexion a la red
 *
 * @param connectivityManager parametro ConnectivityManager de tipo [Context.CONNECTIVITY_SERVICE]
 *
 * @return ConnectionState puede ser [ConnectionState.Available] o [ConnectionState.Unavailable]
 *
 */
private fun getCurrentConnectivityState(connectivityManager: ConnectivityManager): ConnectionState {
    val connected =
        connectivityManager.allNetworks.any { network ->
            connectivityManager.getNetworkCapabilities(network)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                ?: false
        }

    return if (connected) ConnectionState.Available else ConnectionState.Unavailable
}

/**
 * Observador de estado de conexion
 *
 * @return flow con estado de la conexion
 *
 */
@ExperimentalCoroutinesApi
fun Context.observeConnectivityAsFlow() =
    callbackFlow {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val callback = NetworkCallback { connectionState -> trySend(connectionState) }

        val networkRequest =
            NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build()

        connectivityManager.registerNetworkCallback(networkRequest, callback)

        val currentState = getCurrentConnectivityState(connectivityManager)
        trySend(currentState)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }

/**
 * Callback de estado de conexion
 *
 * @return En callBack puede ser [ConnectionState.Available] o [ConnectionState.Unavailable]
 *
 */
fun NetworkCallback(callback: (ConnectionState) -> Unit): ConnectivityManager.NetworkCallback {
    return object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            callback(ConnectionState.Available)
        }

        override fun onLost(network: Network) {
            callback(ConnectionState.Unavailable)
        }
    }
}

/**
 * Fun ext. Formateo  de dato tipo Double en #,###
 *
 * @return String de tipo Doble en formato #,###
 *
 */
fun Double.formatThousand(): String {
    val decimalFormatter = DecimalFormat(Util.PATTERN)
    return decimalFormatter.format(this)
}

/**
 * Fun ext. Formateo de dato tipo String
 *
 * @return String reemplazando (,) con (.)
 *
 */
fun String.clearThousandFormat(): String {
    return this.replace(Util.OLD_VALUE, Util.NEW_VALUE)
}
