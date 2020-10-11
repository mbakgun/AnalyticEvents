package com.dolap.events.ui;

import com.dolap.events.data.model.Banner

interface MainView {
    fun onGetBanners(banners: List<Banner>)
}
