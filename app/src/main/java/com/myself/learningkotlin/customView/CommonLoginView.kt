package com.myself.learningkotlin.customView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.myself.learningkotlin.R
import kotlinx.android.synthetic.main.common_login_view.view.*

/**
 *created by Liang
 *on2020/7/17
 *desc:自定义的登录界面
 */
 class CommonLoginView: FrameLayout {


    constructor(context: Context):this(context,null)
    constructor(context:Context,attr: AttributeSet?):this(context,attr,0)
    constructor(context: Context,attr:AttributeSet?,defstyle:Int):super(context,attr,defstyle) {
        val view = View.inflate(context, R.layout.common_login_view,this)

        btn_sure.setOnClickListener{
           println("success!")
        }
    }


    fun checkIn(account:String):Boolean {
        return account.isEmpty()
    }
}