package suanfaTest

import java.util.*

/**
 *created by Liang
 *on2020/7/21
 *desc:
 */

fun main() {

    val aa = CustomQuene<Int>()
    aa.push(1)
    aa.push(2)
   println( aa.pop())
    aa.push(3)
    aa.push(4)
    aa.push(5)
    aa.push(6)
    aa.push(7)
//   println("2- ${aa.pop()}")

   println("2- ${aa.pop()}")

}

/**
 * 1栈用来存放数据,但他们的特点是先进后出,所以将数据存放到2栈
 * 按照2栈的顺序再取出,那么整体就是队列的先进先出
 */
class CustomQuene<T> {
    private val stack1 = Stack<T>()
    private val stack2 = Stack<T>()

    fun push(arg:T) {
        stack1.push(arg)
    }

    fun pop(): T {
        //2栈是空的清空下,才允许一站的数据进来,否则数据就发生错乱
        if(stack2.size <=0) {
            //只要1栈不为空,那么就将1栈的数据导出,存入2栈
            while (stack1.size !=0) {
               stack2.push(stack1.pop())
            }
        }
        //将2栈的数据导出
        return stack2.pop()

    }

}