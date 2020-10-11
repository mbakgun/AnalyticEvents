package com.dolap.events.helper.extension

import android.graphics.Rect
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dolap.events.helper.util.EndlessRecyclerViewScrollListener
import com.dolap.events.helper.visibleitem.ThrottleTrackingBus
import com.dolap.events.helper.visibleitem.VisibleState

@BindingAdapter("adapter")
fun RecyclerView.adapter(adapter: RecyclerView.Adapter<*>) {
    this.adapter = adapter
}

@BindingAdapter("divider")
fun RecyclerView.divider(height: Int) {
    addItemDecoration(object :
        DividerItemDecoration(context, (layoutManager as LinearLayoutManager).orientation) {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            if (parent.getChildAdapterPosition(view) == state.itemCount - 1)
                outRect.setEmpty()
            else {
                outRect.bottom = height
            }
        }
    })
}

fun RecyclerView.addScrollListener(trackingBus: ThrottleTrackingBus?) =
    addOnScrollListener(object :
        EndlessRecyclerViewScrollListener(layoutManager as LinearLayoutManager) {
        override fun onVisibleItemChanged(state: VisibleState) {
            trackingBus?.postViewEvent(visibleState = state)
        }
    })
