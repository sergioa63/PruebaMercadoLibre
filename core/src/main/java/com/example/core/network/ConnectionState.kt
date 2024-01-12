package com.example.core.network

sealed class ConnectionState {
    object Available : ConnectionState()

    object Unavailable : ConnectionState()
}
