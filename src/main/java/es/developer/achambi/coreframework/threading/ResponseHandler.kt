package es.developer.achambi.coreframework.threading

interface ResponseHandler<T> {
    fun onSuccess(response: T)
    fun onError(error: CoreError) {}
}
