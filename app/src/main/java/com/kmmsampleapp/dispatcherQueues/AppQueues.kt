package com.kmmsampleapp.dispatcherQueues

import android.os.Handler
import android.os.HandlerThread
import java.util.concurrent.ExecutorService
import java.util.concurrent.LinkedBlockingDeque
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

object AppQueues {
    private var dbHandlerThread: HandlerThread? = null
    private var dbHandler: Handler? = null

    private var dbThreadPool: ExecutorService? = null

    fun postToDbHandler(runnable: Runnable?) {
        if (dbHandlerThread == null) {
            dbHandlerThread = HandlerThread("DBHandlerThread")
            dbHandlerThread!!.start()
            dbHandler = Handler(dbHandlerThread!!.looper)
        }
        dbHandler!!.post(runnable!!)
    }

    fun postToDbThreadPool(runnable: Runnable?) {
        if (dbThreadPool == null) {
            dbThreadPool =
                ThreadPoolExecutor(5, 5, 60, TimeUnit.SECONDS, LinkedBlockingDeque())
        }
        dbThreadPool!!.submit(runnable)
    }

}