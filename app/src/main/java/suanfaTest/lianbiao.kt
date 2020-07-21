package suanfaTest

fun main() {

//    nodeTest()
    var i = 100
     change(i)
    println(i)

}

fun change(i: Int) {
    println(i)
    i = 30
    println(i+50)
}

fun nodeTest() {
    var header:Node<String>? = null
    var last:Node<String>? = null      //创建了两个空对象的引用,打印的值也就为  null
    println(header)
    println(last)
    header = Node("wo shi 1")
    println(header)            //创建了一个Node对象,指针指向heaer这个引用,那么header此时就是这个Node的地址

    last = header
    println(last)               //last引用指向了header引用, last拿到了Node得地址,此时last和header共享一个对象
    println(header)
    last.next = Node("wo shi 2")
    last  = last.next
    println(last)

    var currentNode = header
    while (currentNode!=null){
        println(currentNode.item)
        currentNode = currentNode.next
    }
}

class Node<T>(var item:T,var next:Node<T>? = null) {


}