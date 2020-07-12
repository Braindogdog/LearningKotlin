package com.liangpeng.kotlintest.basictest

import android.os.SystemClock
import java.util.function.LongToDoubleFunction

fun main() {
    RenwuxianTest.instance().calculateWithArray1()
    RenwuxianTest.instance().calculateWithArray()
   RenwuxianTest.instance().calculateWithInArray()
   RenwuxianTest.instance().calculateWithIntArray()
   RenwuxianTest.instance().calculateWithList()
   RenwuxianTest.instance().calculateWithList1()
}

class RenwuxianTest private constructor() {

    /**
     * 1.私有化构造主构造函数
     * 2.创建伴生对象,在内创建一个方法创建此类,将实例对象返回
     * 3.在外部调用,直接调用此伴生对象的函数
     */
    companion object {
        fun instance():RenwuxianTest {
            return RenwuxianTest()
        }
    }

    fun calculateWithArray1() {
      val startTime:Long =  System.currentTimeMillis()
       val result =  Array<Int>(100000,init = { i ->
            i+1
        })
        var sum:Long = 0L
//        result.forEach { i ->
//            sum+=i
//             }
        for (item in result) {
            sum+=item
        }

        val timeResult =  System.currentTimeMillis() - startTime
        println("Array平均值:${sum/result.size}, 平均值:${timeResult}")
    }

    fun calculateWithInArray() {
        val startTime:Long = System.currentTimeMillis()
        val result = IntArray(100000,init = {
            it+1
        })

        var sum:Long = 0L
        result.forEach { i ->
            sum += i
        }
        println("InArray平均值:${sum/result.size},用时:${System.currentTimeMillis()-startTime}")

    }

    fun calculateWithList1() {
        val startTime = System.currentTimeMillis()
        val result:List<Int> = List(100000,init = {
            it+1
        })
        var sum = 0L
        result.forEach { sum += it }
        println("List平均值:${sum/result.size} ,用时:${System.currentTimeMillis()-startTime}")
    }













    fun calculateWithArray() {
        val startTime = System.currentTimeMillis()
        val list: Array<Int> = Array(100000, init = { i ->
//            Log.d(TAG, "$i")
            i + 1
        })
        var sum = 0L
        for (i in list) {

            sum += i
        }
        println( sum)
        println(list.size)
        val av = sum / list.size
      println("平均值 = $av, 用时：${System.currentTimeMillis() - startTime}")
    }

    fun calculateWithIntArray() {
        val startTime = System.currentTimeMillis()
        val list: IntArray = IntArray(100000, init = { i ->
//            Log.d(TAG, "$i")
            i + 1
        })
        var sum = 0L
        for (i in list) {
            sum += i
        }
        val av = sum / list.size
       println( "平均值 = $av, 用时：${System.currentTimeMillis() - startTime}")
    }

    fun calculateWithList() {
        val startTime = System.currentTimeMillis()
        val list: List<Int> = List(100000, init = { i ->
//            Log.d(TAG, "$i")
            i + 1
        })
        var sum = 0L
        for (i in list) {
            sum += i
        }
        val av = sum / list.size
        println( "平均值 = $av, 用时：${System.currentTimeMillis() - startTime}")
    }

}