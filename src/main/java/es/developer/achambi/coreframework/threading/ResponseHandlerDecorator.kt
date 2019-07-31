package es.developer.achambi.coreframework.threading


abstract class ResponseHandlerDecorator<T>(private val responseHandler: ResponseHandler<T>)
    : ResponseHandler<T> {

    override fun onSuccess(response: T) {
        responseHandler.onSuccess(response)
    }

    override fun onError(error: Error) {
        responseHandler.onError(error)
    }
}
