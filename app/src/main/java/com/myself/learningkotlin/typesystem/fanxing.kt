package com.myself.learningkotlin.typesystem

fun main() {

//    val list:SmartList<String> = SmartList()
   val list =  arrayListOf<String>("one")
//    list.add("one")
//    list.add("two")
   println(list.find("one"))
   println(list.find("two").isNullOrEmpty())

}



//class SmartList<T> : ArrayList<T>() {
////
////    fun find(t: T):T? {
////        return if(super.indexOf(t)>=0) t else null
////    }
////}

fun<T> ArrayList<T>.find(t:T):T? {
    return if(indexOf(t)>=0) t else null
}