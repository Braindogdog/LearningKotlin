package com.myself.learningkotlin

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class CustomImageView : AppCompatImageView {

    //主构造方法,从构造方法


//    class CustomImageView @JvmOverloads constructor(
//        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
//    ) : AppCompatImageView(context, attrs, defStyleAttr) {

    constructor(context:Context):this(context,null)
    constructor(context: Context,attrs:AttributeSet?):this(context,attrs,0)
    constructor(context: Context,attrs:AttributeSet?,defStyleAttr:Int):super(context,attrs,defStyleAttr)
}