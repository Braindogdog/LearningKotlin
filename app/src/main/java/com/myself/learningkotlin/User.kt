package com.myself.learningkotlin

data class User(
    private var name: String,
    private var age: Int,
    private var job: String,
    private var skin: String
) {

    fun getName():String = name
    fun getAge():Int =age

    fun getJob():String =job
    fun getSkin():String =skin
}