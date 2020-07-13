package com.liangpeng.kotlintest.basictest

import android.nfc.Tag
import android.util.Log
import java.util.*
import kotlin.collections.ArrayList


fun main() {
    val testClass = KotlinTestClass()
//    testClass.testArray()
//    testClass.test1()
//    testClass.mapTest()
//    testClass.testString()
//    testClass.testArraySysmol()
//    testClass.rangeTest()
//    testClass.squenceTest()
//    testClass.listTest()
//    testClass.whenTest(19)
//    testClass.testFor()
    testClass.testTryCatch()

}

const val TAG = "KotlinTestClass"
class KotlinTestClass {




    var str: String? = null
    fun testnullsafe() {
       var length:Int? =  str?.length
       var length2:Int =  str?.length?:-1
    }

    fun testTryCatch()  {

    }

    fun testFor() {
        //循环集合
        var listData = listOf<String>("mike","marry","jonse")
        for (item in listData) println(item)
        //循环一个100次呢
        for (item in 1..100) print("$item ,")
    }


    fun whenTest(num:Int) {
        when(num) {
//           in 0..10 -> println("我在里面")
//            1 -> println("我是1")
//            0,1 -> println("我是0,或者1")
//            in listOf<String>("11","22","33","44","55") -> println("我在集合中")
            else -> print("我是特殊情况")


        }

    }

    fun listTest() {
        val listResult = listOf<Int>(1,2,3,4)
        var result2 = listResult.map { i->
            print("Map $i ,")
            i*2
        }
            .filter { i ->
                print("Filter $i ,")
                i%3 == 0
            }
        println(result2.last())
    }

    fun squenceTest() {
        var squenceResult = sequenceOf(1,2,3,4)
       var result2:Sequence<Int> =  squenceResult.map { i ->
            print("Map $i ,")
            i*2
        }
            .filter { i ->
                print("Filter $i ,")
                i%3 == 0

            }

        println(result2.last())
    }

    fun rangeTest() {
        val rangeNum:IntRange = 1..1000
//        val rangeNum:IntRange = 1 until 1000
//        for (i in rangeNum step 2) println(i)
        for (i in 4 downTo 1) println(i)
    }



    fun testArraySysmol() {
//        strArray1.forEach { i -> println(i) }

        var strArray1 = intArrayOf(1,2,3,4)
       var  strArray2 =  arrayOf(1,2,3,4)
//        strArray2.filter {  }
        val arrayList:ArrayList<String> = ArrayList<String>()
        arrayList.add("1")
        arrayList.add("1")
        arrayList.add("1")
        arrayList.add("1")
        arrayList.forEach { i -> print(i) }
//        val filter: List<Int> = strArray1.filter { i -> i != 1 }
//          var mapResult:List<Int> =  strArray1.map { i -> i+2 }
//       var flatMapResult:List<String> =  strArray1.flatMap { i -> listOf("${i+2}","a") }

//        flatMapResult.forEach { i -> print(i) }
    }







    fun testString() {
        var strings = """ 
                wo hsi yige zho fa.?jn
                dkjfkajdkf adfa
                jdkfjak
                """
        println(strings)
    }

    var v = RenwuxianTest.instance()

    fun testarray1() {

        var arraydata:Array<Int> = arrayOf()
    }


    fun mapTest(){
        var mapData:Map<String,Int> = mapOf("one" to 1,"two" to 2,"three" to 3)
        //取值
        println( mapData.get("one"))
        //取值2
        println(mapData["two"])

        var mapData2 = mutableMapOf("one" to 1,"two" to 2,"three" to 3)
        //修改值
        mapData2.put("one",111)
        //修改值2
        mapData2["two"] = 222
        for (mutableEntry in mapData2) {
            println("---"+mutableEntry.value)
        }
    }


    fun test1() {
      //Error:  Type mismatch: inferred type is Array<Int> but Array<Any> was expected
        var str = arrayOf(1,2,3)
//        var strParent:Array<Any> = str

    }


    fun testArray() {
        intArrayOf()

        var strArray1: Array<String> = arrayOf("mike", "rebort", "rechade")




        println(strArray1.contains("mike"))

        println("contains----" + strArray1.contains("mike"))
        println("get()----" + strArray1.get(1))
        println("size----" + strArray1.size)
        println("first----" + strArray1.first())
        strArray1[0]
        for (strArg in strArray1) {
            println("for each----" + strArg)
        }

        println("set__: " + strArray1.set(0, "Marry"))
        for (s in strArray1) {
            println("changed for each----" + s)
        }
    }


    //    var renwuxianTest = RenwuxianTest()
    companion object {

        const val EAWEC_HAHA: String = "eawec_haha"

    }

    var iKotlinTestClass = object : IKotlinTestClass {
        override fun method1(name: String): String {
            return "匿名内部类"
        }
    }

    fun arrayTest() {
        val arrayOf = arrayOf("1", "2", "3")
        var arrayof2: Array<String> = arrayOf("a", "a")
    }


}


