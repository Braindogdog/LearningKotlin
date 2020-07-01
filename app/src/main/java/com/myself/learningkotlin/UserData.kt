package com.myself.learningkotlin

/**
 *created by Liang
 * on2020/6/30
 */
//class UserData(var height: Int) {     //编译器推荐的构造函数写法
class UserData() {



//    var height:Int
//
//    constructor(height:Int) {
//        this.height = height
//    }
    init {

    }

    var name:String = "Mike"
        get() {
            return field+"--nb"
        }
    set(value) {
        field = "Cute"+value
    }

//    override fun eat() {
//        println("-------eat")
//    }

//    override fun run() {
//        println("------run")
//    }

    fun sleep() {
        println("------sleep")
    }



}