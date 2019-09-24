package com.google.firebase.udacity.daggerPosts

object Util {
    const val BASE_URL = "https://jsonplaceholder.typicode.com"
}

class Hello<T> constructor(val x: T, val j: List<T>) {
    fun letsPlay(hi: T) {
        print(hi.toString())
    }
}



class XX : Comparable<Int> {
    override fun compareTo(other: Int): Int {
        return 0
    }
    fun copy(from: List<*>, to: List<Any>) {

    }
    fun fill(dest: List<out Any>, value: String) {

    }
}

fun main() {
    var li: List<Int> = listOf(1, 2, 3)
    var liS: List<String> = listOf("SS", "FF", "EE")
    var xc = Hello(XX(), li)
    var ww = Hello("123 string", liS)
    xc.letsPlay(123)
    ww.letsPlay("Hmada")
    print(ww.j)

    var y = XX()

    y.copy(li, liS)
    y.fill(li,"ddd")

}