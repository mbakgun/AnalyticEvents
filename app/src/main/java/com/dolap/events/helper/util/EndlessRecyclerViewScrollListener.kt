package com.dolap.events.helper.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dolap.events.helper.visibleitem.VisibleState

abstract class EndlessRecyclerViewScrollListener(private val mLinearLayoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val firstCompletelyVisibleItemPosition =
            mLinearLayoutManager.findFirstCompletelyVisibleItemPosition()
        val lastCompletelyVisibleItemPosition =
            mLinearLayoutManager.findLastCompletelyVisibleItemPosition()

        onVisibleItemChanged(VisibleState(firstCompletelyVisibleItemPosition,
            lastCompletelyVisibleItemPosition))
    }

    abstract fun onVisibleItemChanged(state: VisibleState)
}
