package com.myself.learningkotlin.lamdatest

import android.view.View
import android.widget.Button
import com.myself.learningkotlin.test3

fun main() {

    val test = Test()
//    test.test1(fun(name:String):String {
//        return "我是匿名函数,我作为参数传进来了"
//    })
//
//    val a = fun(name:String):String {
//       return "我是匿名函数,我赋值给变量a了"
//    }
//
//    println(a)

    test eat("apple")
    test.eat("banana")
}

/**
 * 匿名函数,Lambda表达式,高阶函数
 */
class Test {

        infix fun eat(food : String) {
        println("我今天吃了$food")
    }

    lateinit var onClick: (v: View) -> Unit

    fun test1(test2: (name: String) -> String) {
        println("我是test1")
}


}