package com.dolap.events.data

import android.annotation.SuppressLint
import android.util.Log
import androidx.work.ListenableWorker.Result
import androidx.work.impl.utils.futures.SettableFuture

object AnalyticService {
    private const val TAG = "AnalyticService"

    @SuppressLint("RestrictedApi")
    fun sendEvent(bannerId: Int, futureWork: SettableFuture<Result>) {
        Log.d(TAG, "bannerId $bannerId displayed :Sending to server")
        futureWork.set(Result.success())
        Log.d(TAG, "bannerId $bannerId sent")
    }
}
