package com.dolap.events.helper.visibleitem

data class VisibleState @JvmOverloads constructor(val firstCompletelyVisible: Int = -1,
                                                  val lastCompletelyVisible: Int = -1)
