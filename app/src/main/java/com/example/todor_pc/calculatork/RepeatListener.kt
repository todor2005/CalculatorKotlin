package com.example.todor_pc.calculatork

import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnTouchListener

/**
 * @param initialInterval The interval after first click event
 * *
 * @param normalInterval The interval after second and subsequent click
 * *       events
 * *
 * @param clickListener The OnClickListener, that will be called
 * *       periodically
 */
class RepeatListener(private val initialInterval: Int, private val normalInterval: Int,
                     private val clickListener: OnClickListener?) : OnTouchListener {


    private val handler = Handler()

    private val handlerRunnable = object : Runnable {
        override fun run() {
            handler.postDelayed(this, normalInterval.toLong())
            if( clickListener != null ){
                clickListener.onClick(downView)
            }
        }
    }

    private var downView: View? = null

    init {
        if (clickListener == null)
            throw IllegalArgumentException("null runnable")
        if (initialInterval < 0 || normalInterval < 0)
            throw IllegalArgumentException("negative interval")
    }

    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> {
                handler.removeCallbacks(handlerRunnable)
                handler.postDelayed(handlerRunnable, initialInterval.toLong())
                downView = view
                if(clickListener != null){
                    clickListener.onClick(view)
                }
            }
            MotionEvent.ACTION_UP -> {
                handler.removeCallbacks(handlerRunnable)
                downView = null
            }
        }
        return false
    }
}
