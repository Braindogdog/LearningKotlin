package com.myself.learningkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.common_title.*


class MainActivity : AppCompatActivity() {
   lateinit var view :View
    val context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        commonTitleBar.setTitle("I am Title!")
        view = findViewById(R.id.textview)
        commonTitleBar.setRight1Title("rightTitle1")
        tv_title.setOnClickListener { println("click event") }


        //原始写法
//        commonTitleBar.setOnRight1TitleClickListener(object:CommonTitleBar.OnRight1TitleClickListener {
//            override fun onClick() {
//                TODO("Not yet implemented")
//            }
//        })

        commonTitleBar.setOnRight1TitleClickListener {name:String,test:javaClassTest-> Unit
            println("我要执行了额")
        }




    }

}