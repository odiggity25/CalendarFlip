package com.orrie.calendarflip

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.jakewharton.rxbinding2.view.clicks

/**
 * Created by orrie on 2017-07-27.
 */
class CalendarView(context: Context): ConstraintLayout(context) {
    val animates by lazy { findViewById(R.id.animate_button).clicks() }
    private val topFront by lazy { findViewById(R.id.top_front) as ImageView }
    private val topBack by lazy { findViewById(R.id.top_back) as ImageView }
    private val bottomFront by lazy { findViewById(R.id.bottom_front) as ImageView }
    private val bottomBack by lazy { findViewById(R.id.bottom_back) as ImageView }

    init {
        View.inflate(context, R.layout.view_calendar, this)
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        setBackgroundResource(R.color.black)

        topFront.setImageResource(R.drawable.one_top)
        bottomBack.setImageResource(R.drawable.one_bottom)
        CalendarPresener(this)
    }



    fun animateToDay(day: Int) {
        animateTop(day)
    }

    fun animateTop(day: Int) {
        val newTopResource = when (day) {
            0 -> R.drawable.one_top
            1 -> R.drawable.two_top
            2 -> R.drawable.three_top
            else -> R.drawable.one_top
        }

        topBack.setImageResource(newTopResource)

        val topOutAnimation = AnimatorInflater.loadAnimator(context, R.animator.top_out) as AnimatorSet
        topFront.pivotY = topFront.height.toFloat()
        topOutAnimation.setTarget(topFront)
        topOutAnimation.start()
        topOutAnimation.addListener(object: SimpleAnimationListener() {
            override fun onAnimationEnd(animation: Animator?) {
                topFront.setImageResource(newTopResource)
                animateBottom(day)
            }
        })
    }

    fun animateBottom(day: Int) {
        val newBottomResource = when (day) {
            0 -> R.drawable.one_bottom
            1 -> R.drawable.two_bottom
            2 -> R.drawable.three_bottom
            else -> R.drawable.one_bottom
        }

        bottomFront.setImageResource(newBottomResource)

        val bottomInAnimation = AnimatorInflater.loadAnimator(context, R.animator.bottom_in) as AnimatorSet
        bottomFront.pivotY = 0.toFloat()
        bottomInAnimation.setTarget(bottomFront)
        bottomInAnimation.start()
        bottomInAnimation.addListener(object: SimpleAnimationListener() {
            override fun onAnimationEnd(animation: Animator?) {
                bottomBack.setImageResource(newBottomResource)
            }
        })
    }
}