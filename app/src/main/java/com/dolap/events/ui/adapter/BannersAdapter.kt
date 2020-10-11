package com.dolap.events.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dolap.events.data.model.Banner

class BannersAdapter(private val banners: MutableList<Banner> = mutableListOf()) :
    RecyclerView.Adapter<BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BannerViewHolder.create(parent)

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) =
        holder.bind(banners[position])

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount() = banners.size

    fun updateList(bannerList: List<Banner>) {
        banners.addAll(bannerList)
        notifyDataSetChanged()
    }
}
