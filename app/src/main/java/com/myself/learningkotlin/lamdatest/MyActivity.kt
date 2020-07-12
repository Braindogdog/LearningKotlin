package com.myself.learningkotlin.lamdatest

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MyActivity:AppCompatActivity() {


    private lateinit var onClick: (v: View) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

//        val button:Button = Button(this)
//        button.setOnClickListener({v:View -> {
//            .....
//        })
    }

//    fun setOnClickListener1(onClick:(v: View) ->Unit) {
//            this.onClick = onClick
//    }

}