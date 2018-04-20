package ru.whalemare.kutils

/**
 * @since 2018
 * @author Anton Vlasov - whalemare
 */
//fun RecyclerView.enableAutoScroll(): Handler {
//    val speedScroll = 4000L
//    val handler = Handler()
//    val runnable = object : Runnable {
//        internal var count = 0
//        override fun run() {
//            if (count == adapter.itemCount) count = 0
//
//            if (count < adapter.itemCount) {
//                smoothScrollToPosition(++count)
//                handler.postDelayed(this, speedScroll)
//            }
//        }
//    }
//    handler.postDelayed(runnable, 0)
//
//    setOnTouchListener { v, event ->
//        handler.removeCallbacksAndMessages(null)
//        return@setOnTouchListener false
//    }
//
//    return handler
//}
//
//fun RecyclerView.addOnScrollReachedEndListener(onReached: () -> Unit) {
//    val canScrollUp = -1
//    val canScrollDown = 1
//    addOnScrollListener(object : RecyclerView.OnScrollListener() {
//        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//            super.onScrollStateChanged(recyclerView, newState)
//            if (!recyclerView.canScrollVertically(canScrollDown)) {
//                onReached.invoke()
//            }
//        }
//    })
//}

