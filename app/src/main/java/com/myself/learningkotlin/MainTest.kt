package com.myself.learningkotlin

/**
 *created by Liang
 * on2020/6/30
 */


fun main() {


//    testCast()
//    testgetset()
    //测试方法的默认参数和具名参数
//    test(age = 12,isBeatuful = false)
    //测试类的构造函数的默认参数和具名参数
    test()

}

fun test() {
   val constructorTest = ConstructorTest("liangpeng",12,true)
//    constructorTest.say()
}










//
//    fun test( name:String= "liangpeng", age:Int = 0, isBeatuful: Boolean= true) {
//        println("name:"+name)
//        println("age:"+age)
//        println("isBea:"+isBeatuful)
//    }

fun testCast() {
    //    var user:IMainActiity? = UserData()
    var user:UserData? = UserData()
    user = null
//    if(user is UserData) {
//       user.sleep()
//    }
//    (user as UserData).sleep()    // Exception in thread "main" kotlin.TypeCastException: null cannot be cast to non-null type com.myself.learningkotlin.UserData
//    (user as? UserData)?.sleep()      //不报错
    (user as UserData?)?.sleep()

}

fun testgetset() {
    val user = UserData()
//    user.name = "Marry"
    println(user.name)
}
class MainTest {


}