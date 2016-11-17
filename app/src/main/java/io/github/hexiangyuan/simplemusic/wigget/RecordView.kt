package io.github.hexiangyuan.simplemusic.wigget

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.animation.LinearInterpolator
import android.widget.ImageView


class RecordView : ImageView {

    var animator: ObjectAnimator? = null

    constructor(context: Context) : super(context) {
        initAnimator()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAnimator()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        initAnimator()
    }

    fun initAnimator() {
        animator = ObjectAnimator.ofFloat(this, "rotation", 0f, 359f)
        animator!!.duration = 3000
        animator!!.repeatCount = -1
        animator!!.interpolator = LinearInterpolator()
        animator!!.target = this
    }

    private var mCurrentPlayTime = 0L

    fun pause() {
        mCurrentPlayTime = animator!!.currentPlayTime
        animator!!.cancel()
    }

    fun resume() {
        animator!!.start()
        animator!!.currentPlayTime = mCurrentPlayTime
    }
}
