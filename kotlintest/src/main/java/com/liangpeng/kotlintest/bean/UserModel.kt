package com.liangpeng.kotlintest.bean

import androidx.databinding.ObservableField

class UserModel constructor(
    private var name: String,
    private var age: Int
) {
   val n =  ObservableField<String>(name)
    val a = ObservableField<Int>(age)

    fun setName(name: String) {
        this.name = name
    }

    fun getName(): String {
        return name
    }

    fun setAge(age: Int) {
        this.age = age
    }


    fun getAge():Int =  age


}