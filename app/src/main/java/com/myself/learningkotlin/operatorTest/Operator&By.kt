package com.myself.learningkotlin.operatorTest

/**
 *created by Liang
 *on2020/7/17
 *desc:
 */
import kotlin.reflect.KProperty
//类的委托
fun main() {
    var myson = MySon()
    //爷爷奶奶给了100块
    myson.money =100
    //取压岁钱
    println(myson.money)
}

class MySon {
    //压岁钱
    var money: Int by Mother()
}

class Mother {
    //儿子的压岁钱
    var mySonMoney = 0
    //自己的钱包,妈妈的钱包
    var myWallt = 0
    operator fun getValue(myson: MySon, p: KProperty<*>): Int {
        return mySonMoney;
    }
    operator fun setValue(myson: MySon, p: KProperty<*>, i: Int) {
        mySonMoney = 50
        myWallt += i - 50
    }
}