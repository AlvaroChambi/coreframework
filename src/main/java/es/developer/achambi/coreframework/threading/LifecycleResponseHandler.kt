package es.developer.achambi.coreframework.threading

import android.arch.lifecycle.Lifecycle

class LifecycleResponseHandler<T>
(private val lifecycle: Lifecycle, responseHandler: ResponseHandler<T>)
    : ResponseHandlerDecorator<T>(responseHandler) {

    override fun onSuccess(response: T) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            super.onSuccess(response)
        }
    }

    override fun onError(error: Error) {
        if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
            super.onError(error)
        }
    }
}
