package com.harismexis.rates.util

import android.animation.*
import android.view.View

fun playAnimation(
    view: View,
    onComplete: () -> Unit
) {
    val scaleAnim = ObjectAnimator.ofPropertyValuesHolder(
        view,
        PropertyValuesHolder.ofFloat("scaleX", 0.9f),
        PropertyValuesHolder.ofFloat("scaleY", 0.9f)
    )
    scaleAnim.duration = 400
    scaleAnim.repeatMode = ValueAnimator.REVERSE
    scaleAnim.repeatCount = 1
    scaleAnim.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator?) {
            view.visibility = View.GONE
            onComplete()
        }
    })
    view.visibility = View.VISIBLE
    scaleAnim.start()
}
