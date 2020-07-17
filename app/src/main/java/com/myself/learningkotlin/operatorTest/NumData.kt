package com.myself.learningkotlin.operatorTest

fun main() {
    val point = NumData(10, 20)
//    println(-NumData(10, 20))

    val point2 = NumData2(10)
   val b =  point2.plus(20)
    println()
}


/**
 *created by Liang
 *on2020/7/17
 *desc:
 */
data class NumData(val x: Int, val y: Int)

//这是一个NumData内部的成员函数
//    operator fun unaryMinus(): Any? = NumData(-x,-y)


//这是一个NumData的扩展函数
operator fun NumData.unaryMinus() {
    NumData(-x, -y)
}


data class NumData2(val a:Int){

    operator fun plus(b:Int):Any {
        return NumData2(a+b)
    }
}

