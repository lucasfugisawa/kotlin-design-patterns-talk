package com.fugisawa.dp1_singleton

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
