package com.dolap.events.data.workmanager

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class EventWorkManager(appContext: Context) {
    private val instance by lazy { WorkManager.getInstance(appContext) }

    fun sendEvent(id: Int) = instance.beginUniqueWork(
        TAG,
        ExistingWorkPolicy.APPEND,
        buildEvent(id)
    ).enqueue()

    private fun buildEvent(id: Int): OneTimeWorkRequest =
        OneTimeWorkRequest.Builder(EventWorker::class.java)
            .setInitialDelay(INITIAL_DELAY, TimeUnit.SECONDS)
            .setInputData(Data.Builder().putInt(DATA_TAG, id).build())
            .setConstraints(
                Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
            )
            .build()

    companion object {
        private const val TAG: String = "EventWorkManager"
        private const val INITIAL_DELAY = 6L
        const val DATA_TAG = "BannerId_EventWorkManager"
    }
}
