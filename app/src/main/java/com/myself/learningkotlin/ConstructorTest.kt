package com.myself.learningkotlin

//参数列表中,是否写val/var,是由区别的
class ConstructorTest( name:String="flaminggo",  age:Int,  isbadMan:Boolean= false) {

//    fun say() {
//        println(name)
//        println(age)
//        println(isbadMan)
//    }

    //参数列表中使用了val/var,就等于在类的内部声明了一个同名的属性,我们可以用this进行调用
    //如果没有声明:          init{...}是先与成员变量执行的

    var name:String
    var age:Int
    var isbadMan:Boolean

    init{
        this.name = name
        this.age = age
        this.isbadMan = isbadMan
    }
}