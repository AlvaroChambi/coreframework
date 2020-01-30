package es.developer.achambi.coreframework.threading

class CoreError(message: String? = "",
                val type: String? = null) : Exception(message)
