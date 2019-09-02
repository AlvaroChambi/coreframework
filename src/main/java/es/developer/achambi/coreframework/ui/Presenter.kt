package es.developer.achambi.coreframework.ui

import androidx.lifecycle.Lifecycle
import es.developer.achambi.coreframework.threading.*

abstract class Presenter<T: Screen>(protected val screen: T,
                                    private val lifecycle : Lifecycle,
                                    private val executor: ExecutorInterface) {
    /**
     * Perform the request in the main executor, response handler will be a lifecycle aware one.
     * @param request Request to be performed
     * @param responseHandler response handler
     */
    protected fun <D> request(request: Request<D>, responseHandler: ResponseHandler<D>) {
        executor.executeRequest(request, LifecycleResponseHandler(
                lifecycle, responseHandler
        ))
    }
}
