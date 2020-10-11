package com.dolap.events.data

import android.os.SystemClock
import com.dolap.events.data.model.Banner
import com.dolap.events.data.model.BannersResponse
import io.reactivex.Observable
import io.reactivex.Observable.create

object BannerService {
    private const val imageUrl = "https://dummyimage.com/{size}/60D4AE/fff.png"
    private val images = listOf("400x100", "400x200", "400x300", "400x400", "400x500")
    private var pageNumber = 0

    fun getBanners(capacity: Int = 20): Observable<BannersResponse> =
        create { emitter ->
            SystemClock.sleep(2000)
            emitter.onNext(BannersResponse((1..capacity).map { index ->
                Banner(
                    id = pageNumber * capacity + index,
                    url = imageUrl.replace("{size}", images.random())
                )
            }))
            pageNumber++
            emitter.onComplete()
        }
}
