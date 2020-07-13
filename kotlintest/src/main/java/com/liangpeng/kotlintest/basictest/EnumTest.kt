package com.liangpeng.kotlintest.basictest

/**
 *created by Liang
 *on2020/7/13
 *desc:
 */

enum class WeekDay(
    private var day:Int
) {

    MON(1),
    TUE(2),
    WEN(3),
    THU(4),
    FRI(5),
    STA(6),
    SUN(7)
    ;

    fun getDay():Int {
        return day
    }

    fun test() {
        mapOf("one" to 1,"two" to 2)
    }

}