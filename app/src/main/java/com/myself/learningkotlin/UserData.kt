package com.myself.learningkotlin

/**
 *created by Liang
 * on2020/6/30
 */
class UserData : UserDataSuper(){

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

    override fun run() {
        println("------run")
    }

    fun sleep() {
        println("------sleep")
    }



}