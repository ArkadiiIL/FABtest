package com.arkadii.buttontest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private var startX = 0f
    private var startY = 0f


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val moveButton = findViewById<FloatingActionButton>(R.id.moveButton)
        moveButton.setOnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    val location = IntArray(2) {0}
                    view.getLocationInWindow(location)
                    startX = location[0].toFloat()
                    startY = location[1].toFloat()
                }
                MotionEvent.ACTION_UP -> {
                    view.animate().let { animator ->
                        animator.x(startX)
                        animator.y(startY)
                        animator.duration = 0
                        animator.start()
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    view.animate().let { animator ->
                        animator.x(event.rawX)
                        animator.y(event.rawY)
                        animator.duration = 0
                        animator.start()
                    }
                }
            }
            false
        }
    }
}