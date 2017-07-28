package com.orrie.calendarflip

import android.animation.Animator

/**
 * Simple animation listener so you only need to override the methods you want
 * Created by orrie on 2017-07-28.
 */
open class SimpleAnimationListener: Animator.AnimatorListener {
    override fun onAnimationRepeat(animation: Animator?) {}

    override fun onAnimationEnd(animation: Animator?) {}

    override fun onAnimationCancel(animation: Animator?) {}

    override fun onAnimationStart(animation: Animator?) {}
}