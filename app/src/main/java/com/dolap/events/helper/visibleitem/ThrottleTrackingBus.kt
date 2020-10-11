package com.dolap.events.helper.visibleitem

import rx.Subscription
import rx.functions.Action1
import rx.subjects.PublishSubject
import rx.subjects.Subject
import java.util.concurrent.TimeUnit

class ThrottleTrackingBus(private val onSuccess: Action1<Set<Int>>) {

    private val publishSubject: Subject<VisibleState, VisibleState>
    private val subscription: Subscription
    private var recentVisibleState = VisibleState()

    init {
        publishSubject = PublishSubject.create()
        subscription = publishSubject
            .distinctUntilChanged()
            .throttleWithTimeout(THRESHOLD_MS.toLong(), TimeUnit.MILLISECONDS)
            .subscribe(this@ThrottleTrackingBus::onCallback)
    }

    fun postViewEvent(visibleState: VisibleState) = publishSubject.onNext(visibleState)

    fun unsubscribe() = subscription.unsubscribe()

    private fun onCallback(visibleState: VisibleState) {
        val recentRange =
            recentVisibleState.firstCompletelyVisible..recentVisibleState.lastCompletelyVisible
        val currentRange = visibleState.firstCompletelyVisible..visibleState.lastCompletelyVisible
        val subtractItems = currentRange.subtract(recentRange)
        if (subtractItems.isNotEmpty()) {
            recentVisibleState = visibleState
            if (subtractItems.contains(-1).not())
                onSuccess.call(subtractItems)
        }
    }

    companion object {
        private const val THRESHOLD_MS = 250
    }
}
