package es.developer.achambi.coreframework.threading

interface Request<T> {
    @Throws(Error::class)
    fun perform(): T
}
