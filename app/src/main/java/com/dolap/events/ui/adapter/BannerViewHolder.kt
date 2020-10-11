package com.dolap.events.ui.adapter

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dolap.events.data.model.Banner
import com.dolap.events.databinding.ItemBannerBinding
import com.dolap.events.databinding.ItemBannerBinding.inflate

class BannerViewHolder(private val binding: ItemBannerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Banner) {
        binding.item = item
        binding.executePendingBindings()
    }

    companion object {
        fun create(parent: ViewGroup): BannerViewHolder =
            BannerViewHolder(inflate(from(parent.context)))
    }
}
