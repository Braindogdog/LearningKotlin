package com.myself.learningkotlin

/**
 *created by Liang
 * on2020/6/30
 */


fun main() {


//    testCast()
//    testgetset()
}

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