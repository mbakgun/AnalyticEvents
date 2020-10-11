package com.dolap.events.data.workmanager

import android.annotation.SuppressLint
import android.content.Context
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters
import androidx.work.impl.utils.futures.SettableFuture
import com.dolap.events.data.AnalyticService.sendEvent
import com.dolap.events.data.workmanager.EventWorkManager.Companion.DATA_TAG
import com.google.common.util.concurrent.ListenableFuture

@SuppressLint("RestrictedApi")
class EventWorker(
    context: Context,
    params: WorkerParameters
) : ListenableWorker(context, params) {

    @Suppress("TooGenericExceptionCaught")
    override fun startWork(): ListenableFuture<Result> {
        val futureWork: SettableFuture<Result> = SettableFuture.create()
        val bannerId = getEvent(inputData)
        if (bannerId != null) {
            sendEvent(bannerId, futureWork)
        } else {
            futureWork.set(Result.failure())
        }
        return futureWork
    }

    private fun getEvent(inputData: Data): Int? {
        val bannerId = inputData.getInt(DATA_TAG, -1)
        if (bannerId == -1) {
            return null
        }
        return bannerId
    }
}
