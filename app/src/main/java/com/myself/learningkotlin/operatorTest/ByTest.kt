package com.myself.learningkotlin.operatorTest

import android.telephony.IccOpenLogicalChannelResponse

/**
 *created by Liang
 *on2020/7/17
 *desc:
 */

fun main() {
//    val factorial = Factorial(object : ICallBack{
//        override fun dosomething() {
//            println("I am doing something")
//        }
//    })
//    factorial.dosomething()

    val factorial2 = Factorial3()
    FactoryialProxy(factorial2).dododo()
    FactoryialProxy(factorial2).dosomething()
}

class Factorial(private val callBack:ICallBack):ICallBack{


    override fun dosomething() {
        println("------before")
        callBack.dosomething()
        println("-------after")
    }

}

class Factorial2():ICallBack{


    override fun dosomething() {
        println("------before")
        println("-------after")
    }

}

class Factorial3:ICallBack {
    override fun dosomething() {
        println("wo zai 3 limian   dododo")
    }
}


class FactoryialProxy(val icall:ICallBack):ICallBack by icall {

    fun dododo() {
        println("I a    n dododo")
    }
}