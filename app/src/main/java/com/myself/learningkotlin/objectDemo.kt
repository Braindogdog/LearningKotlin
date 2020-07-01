package com.myself.learningkotlin

//创建一个类,并声明他的对象,可用作单例,里面的方法和变量均为静态方法和静态变量
//object NetWorkUtils {
//
//    val name:String = "liangpeng"
//    fun eat():String = "我今天吃了两碗饭"
//
//}

//如果不需要object修饰类,但又希望使用静态变量和静态函数
//class NetWorkUtils {
//    object Utils {
//        val name:String = "liangpeng"
//    fun eat():String = "我今天吃了两碗饭"
//    }
//}

//使用伴生对象来完成  并定义常量
class NetWorkUtils {
    companion object {
        const val TAG = "我是一个常量"
        val name:String = "liangpeng"
         fun eat():String = "我今天吃了两碗饭"
    }
}


fun main() {
    //测试object修饰的类中,为函数变量和函数方法
//    test1()
    //测试普通class中得静态函数和静态变量
//    test2()
    test3()


}
//代码块只有一行的写法
//fun test3() = println(NetWorkUtils.TAG)

fun test3() {
    println(NetWorkUtils.name)
    println(NetWorkUtils.eat())
}

//fun test2() {
//    println( NetWorkUtils.Utils.name)
//    println( NetWorkUtils.Utils.eat())
//
//}

//fun test1() {
//    println(NetWorkUtils.name)
//    println(NetWorkUtils.eat())
//}