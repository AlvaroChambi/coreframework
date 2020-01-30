package es.developer.achambi.coreframework.threading

interface Request<T> {
    @Throws(CoreError::class)
    fun perform(): T
}
