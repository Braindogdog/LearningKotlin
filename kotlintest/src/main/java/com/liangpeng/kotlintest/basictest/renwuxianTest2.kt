package com.liangpeng.kotlintest.basictest

fun main() {

//    val renwuxianTest2 = renwuxianTest2(Person("liangpeng", 29))
    val renwuxianTest2 = renwuxianTest2("liangpeng",29)
    renwuxianTest2.filterTest()

}

class renwuxianTest2 constructor(var name: String) {

//    lateinit var person: Person
    var age:Int = 0

    constructor(name: String, age: Int) : this(name) {
        this.age = age
    }

    constructor(person: Person) : this(person.name, person.age) {
//        this.person = person
    }

    fun show() {
//        println("${person.name} ${person.age}")
        println("$name $age")
    }

    fun filterTest() {
        val result = listOf<Int>(21,40,11,33,78)
//       val result2 =  result.filter { i ->
//            i%3 == 0
//        }
//        for (item in result2) {
//            println(item)
//        }

        result.filter {
            it%3==0
        }
            .forEach {
                println(it)
            }

    }
}

class Person(var name: String, var age: Int) {

}




