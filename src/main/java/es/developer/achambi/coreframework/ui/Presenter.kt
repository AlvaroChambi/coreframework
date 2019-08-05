package es.developer.achambi.coreframework.ui

import android.arch.lifecycle.Lifecycle
import es.developer.achambi.coreframework.threading.LifecycleResponseHandler
import es.developer.achambi.coreframework.threading.MainExecutor
import es.developer.achambi.coreframework.threading.Request
import es.developer.achambi.coreframework.threading.ResponseHandler

abstract class Presenter<T: Screen>(protected val screen: T,
                                    private val lifecycle : Lifecycle,
                                    private val executor: MainExecutor) {
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
