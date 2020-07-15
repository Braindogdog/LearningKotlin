package com.myself.androidbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button:View = LayoutInflater.from(this).inflate(R.layout.only_button,null,false)
        ll.addView(button)
       println("---------${parentaa.parent}")
    }
}
