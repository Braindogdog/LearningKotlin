package com.myself.learningkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView


class MainActivity : AppCompatActivity() {
   lateinit var view :View
    var strnull:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view = findViewById(R.id.textview)
        //不可空变量传递给可空参数
       test1(view)
        //可空变量传递给不可空参数         这是错误的
        //可空变量传递给可空参数               这是正确的
        test3(strnull)
        test2(66)

    }
    fun test3(args:String?) {

    }

    fun test1(args:View?) {
        println("------"+args?.id)
    }

    fun test2(args:Int) {
        println("-----"+args)
        //为什么没有执行下面的
        print("-----"+args)
    }
}