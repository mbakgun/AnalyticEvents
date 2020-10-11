package com.dolap.events.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil.setContentView
import com.dolap.events.R.layout.activity_main
import com.dolap.events.data.model.Banner
import com.dolap.events.data.workmanager.EventWorkManager
import com.dolap.events.databinding.ActivityMainBinding
import com.dolap.events.helper.extension.addScrollListener
import com.dolap.events.helper.visibleitem.ThrottleTrackingBus
import com.dolap.events.ui.adapter.BannersAdapter


class MainActivity : AppCompatActivity(), MainView {
    private val presenter by lazy { MainPresenter(this) }
    private lateinit var binding: ActivityMainBinding
    var trackingBus: ThrottleTrackingBus? = null
    val workManager by lazy { EventWorkManager(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = setContentView(this, activity_main)
        binding.adapter = BannersAdapter()
        trackingBus = ThrottleTrackingBus(this::onBannerItemDisplayed)
        binding.recyclerViewBanners.addScrollListener(trackingBus)

        presenter.getBanners()
    }

    override fun onStop() {
        presenter.onStop()
        trackingBus?.unsubscribe()
        super.onStop()
    }

    override fun onGetBanners(banners: List<Banner>) {
        binding.progressBar.isVisible = false
        binding.adapter?.updateList(banners)
    }

    private fun onBannerItemDisplayed(visibleItems: Set<Int>) =
        visibleItems.forEach { workManager.sendEvent(it) }
}
