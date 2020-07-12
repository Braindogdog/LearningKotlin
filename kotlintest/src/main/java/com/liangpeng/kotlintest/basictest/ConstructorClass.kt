package com.liangpeng.kotlintest.basictest



class TestClass(var name:String) {

    //直接调用主构造函数
    constructor(name:String,age:Int):this(name) {

    }

    //这里调用了上面的次级构造函数,属于间接调用主构造函数
    constructor(name:String,age:Int,id:String):this(name,age) {

        /**
         * 命名参数,位置参数调用的时候有个主意事项,位置参数必须放在命名参数的前面
         */
        namedParams("huhu", age = 20, id = "hehe")
    }




    fun namedParams(name:String = "haha",age:Int,id:String="123"){

    }

}