package com.harismexis.rates.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest

class NetworkConnectionListener(
    private var client: NetworkConnectionClient
) : NetworkCallback() {

    interface NetworkConnectionClient {
        fun onNetworkConnectionAvailable()
        fun onNetworkConnectionLost()
    }

    private val networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    fun startListen() {
        val connectivityManager = (client as Context)
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(networkRequest, this)
    }

    fun stopListen() {
        val connectivityManager = (client as Context)
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.unregisterNetworkCallback(this)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        client.onNetworkConnectionAvailable()
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        client.onNetworkConnectionLost()
    }

}