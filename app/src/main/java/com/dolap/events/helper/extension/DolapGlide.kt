package com.dolap.events.helper.extension

import android.content.Context
import android.content.res.Resources
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target

@GlideModule
class DolapGlide : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        val options = RequestOptions().format(DecodeFormat.PREFER_ARGB_8888).skipMemoryCache(true)
        builder.setDefaultRequestOptions(options)
    }
}

@BindingAdapter("imageUrl")
fun ImageView.load(uri: String) =
    GlideApp.with(this)
        .load(uri)
        .override(Resources.getSystem().displayMetrics.widthPixels,
            Target.SIZE_ORIGINAL)
        .dontTransform()
        .into(this)
