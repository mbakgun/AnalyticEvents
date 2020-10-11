package com.dolap.events.ui;

import com.dolap.events.data.BannerService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view: MainView) {
    private val compositeDisposable = CompositeDisposable()

    fun getBanners() {
        compositeDisposable.add(
            BannerService.getBanners()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { response ->
                    response?.let { view.onGetBanners(it.banners) }
                }
        )
    }

    fun onStop() = compositeDisposable.clear()
}
