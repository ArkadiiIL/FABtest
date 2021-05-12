package com.arkadii.buttontest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private var startX = 0f
    private var startY = 0f


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val moveButton = findViewById<FloatingActionButton>(R.id.moveButton)
        moveButton.setOnTouchListener { view, event ->
            val uncheckParent = view.parent
            if(uncheckParent is View) {
                val parentLocation = IntArray(2)
                uncheckParent.getLocationOnScreen(parentLocation)
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
                        animator.y(abs(startY - parentLocation[1]))
                        animator.duration = 0
                        animator.start()
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    view.animate().let { animator ->
                        animator.x(event.rawX)
                        animator.y(event.rawY - parentLocation[1])
                        animator.duration = 0
                        animator.start()
                    }
                }
            }
            }
            false
        }


        val moveButton2 = findViewById<FloatingActionButton>(R.id.moveButton2)
        moveButton2.setOnTouchListener {view, event ->
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