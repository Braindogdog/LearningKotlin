package com.myself.learningkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.common_title.*


class MainActivity : AppCompatActivity() {
    lateinit var view: View
    val context = this
    private var data:List<User>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        commonTitleBar.setTitle("I am Title!")
        view = findViewById(R.id.textview)
        commonTitleBar.setRight1Title("rightTitle1")
        tv_title.setOnClickListener { println("click event") }
        initData()
        initRecyclerView()

//        var user= data?.get(0)?.let { User(it.getName(),it.getAge(),it.getJob(),it.getSkin()) }



        //原始写法
//        commonTitleBar.setOnRight1TitleClickListener(object:CommonTitleBar.OnRight1TitleClickListener {
//            override fun onClick() {
//
//            }
//        })

        commonTitleBar.setOnRight1TitleClickListener { name: String, test: javaClassTest ->
            Unit
            println("我要执行了额")
        }


    }

    private fun initData() {
        val person1 = User("liangpeng", 12, "develpment", "yellow")
        val person2 = User("Trup", 12, "leader", "white")
        val person3 = User("Jordan", 12, "baskBallStar", "black")
        val person4 = User("aFanDa", 12, "Canvas", "blue")
        data = listOf<User>(person1,person2,person3,person4)
    }


    private fun initRecyclerView() {
        recycler_show_data.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recycler_show_data.adapter = MyAdapter(this,data)
    }

}