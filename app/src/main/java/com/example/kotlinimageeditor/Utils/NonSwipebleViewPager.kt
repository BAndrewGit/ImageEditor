package com.example.kotlinimageeditor.Utils

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller
import androidx.viewpager.widget.ViewPager
import java.lang.reflect.Field

class MyScroller(context: Context): Scroller(context, DecelerateInterpolator()) {
    override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
        super.startScroll(startX, startY, dx, dy, 400)
    }
}

class NonSwipebleViewPager:ViewPager {
    constructor(context: Context) :super(context){
        setMyScroller()
    }

    constructor(context: Context, attributeSet: AttributeSet) :super(context, attributeSet){
        setMyScroller()
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    private fun setMyScroller() {
        try {
            val viewPager : Class<ViewPager> = ViewPager::class.java
            val scroller : Field = viewPager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(this,MyScroller(context))
        }catch (e:Exception)
        {
            e.printStackTrace()
        }
    }
}