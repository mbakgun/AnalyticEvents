package com.dolap.events.data.model

data class BannersResponse(val banners: List<Banner>)
data class Banner(val id: Int, val url: String)
