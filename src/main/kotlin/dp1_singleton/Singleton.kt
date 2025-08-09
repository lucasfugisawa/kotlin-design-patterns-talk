package com.fugisawa.dp1_singleton

// Problema: instância única e acessível.

class DriverManager private constructor() {
    companion object {
        // Não é lazy/thread-safe
        private var instance: DriverManager? = null

        fun getInstance(): DriverManager {
            if (instance == null) {
                instance = DriverManager()
            }
            return instance!!
        }
    }

    // Carrega configurações do singleton...
}

fun main() {
    val driverManager = DriverManager.getInstance()
}
