package com.myself.learningkotlin

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.common_title.view.*

/**
 *created by Liang
 *on2020/7/16
 *desc: 自定义的组合ViewGroup
 */
class CommonTitleBar: FrameLayout {


    private  var listener:OnRight1TitleClickListener? = null
    private  var listener2: ((name:String,test:javaClassTest) -> Unit?)? = null

    constructor(context: Context):this(context,null) {

    }

    constructor(context: Context,attr: AttributeSet?):this(context,attr,0) {

    }

    constructor(context: Context,attr: AttributeSet?,defstyle:Int) : super(context,attr,defstyle) {
        val view:View = View.inflate(context,R.layout.common_title,this)

        tv_title_right.setOnClickListener() {
//            if(null!= listener) listener?.onClick()
            if(null!= listener2) listener2?.invoke("liangpeng", javaClassTest())
        }
    }



    fun setTitle(title:String) {
        if(null!=tv_title) {
            tv_title.text = title
        }
    }

    fun setRight1Title(title:String) {
        if(null!=tv_title_right) {
            tv_title_right.visibility = View.VISIBLE
            tv_title_right.text = title
        }
    }

    fun setOnRight1TitleClickListener(listener:OnRight1TitleClickListener) {
        this.listener = listener
    }

    fun setOnRight1TitleClickListener(listener:(name:String,test:javaClassTest)->Unit) {
        this.listener2 = listener
    }


    interface OnRight1TitleClickListener {
        fun onClick()
    }




}