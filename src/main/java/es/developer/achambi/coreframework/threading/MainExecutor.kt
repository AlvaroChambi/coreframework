package es.developer.achambi.coreframework.threading

import android.os.Handler
import android.os.Looper
import com.crashlytics.android.Crashlytics
import java.lang.Exception

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class MainExecutor private constructor(corePoolSize: Int, maximumPoolSize: Int, keepAliveTime: Long,
                                            unit: TimeUnit, workQueue: LinkedBlockingQueue<Runnable>)
    : ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue),
        ExecutorInterface {

    override fun <T> executeRequest(request: Request<T>, responseHandler: ResponseHandler<T>?) {
        execute {
            try {
                postSuccessOnUI(request.perform(), responseHandler)
            } catch (e: CoreError) {
                postErrorOnUI(e, responseHandler)
                Crashlytics.logException(e)
                e.printStackTrace()
            } catch (e:Exception) {
                postErrorOnUI(CoreError(e.toString()), responseHandler)
                Crashlytics.logException(e)
                e.printStackTrace()
            }
        }
    }

    private fun <T> postErrorOnUI(error: CoreError,
                                  responseHandler: ResponseHandler<T>?) {
        MAIN_HANDLER.post { responseHandler?.onError(error) }
    }

    private fun <T> postSuccessOnUI(response: T,
                                responseHandler: ResponseHandler<T>?) {
        MAIN_HANDLER.post { responseHandler?.onSuccess(response) }
    }

    companion object {
        private const val KEEP_ALIVE_TIME: Long = 0
        private const val THREAD_NUMBERS = 4
        private val MAIN_HANDLER = Handler(Looper.getMainLooper())

        fun buildExecutor(): MainExecutor {
            return MainExecutor(THREAD_NUMBERS, THREAD_NUMBERS,
                    KEEP_ALIVE_TIME,
                    TimeUnit.MILLISECONDS,
                    LinkedBlockingQueue())
        }
    }
}
