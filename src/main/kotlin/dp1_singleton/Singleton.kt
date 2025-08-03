package com.fugisawa.dp1_singleton

class ConfigurationManager private constructor() {
    companion object {
        // Não é lazy/thread-safe
        private var instance: ConfigurationManager? = null

        fun getInstance(): ConfigurationManager {
            if (instance == null) {
                instance = ConfigurationManager()
            }
            return instance!!
        }
    }

    // Carrega configurações do singleton...
}

fun main() {
    val configurationManager = ConfigurationManager.getInstance()
}