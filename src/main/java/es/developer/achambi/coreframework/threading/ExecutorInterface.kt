package es.developer.achambi.coreframework.threading

interface ExecutorInterface {
    fun <T> executeRequest(request: Request<T>, responseHandler: ResponseHandler<T>)
}

class MockExecutor : ExecutorInterface {
    override fun <T> executeRequest(request: Request<T>, responseHandler: ResponseHandler<T>) {
        try {
            responseHandler.onSuccess(request.perform())
        } catch (error: Error) {
            responseHandler.onError(error)
        }
    }

}