package com.liangpeng.kotlintest.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.liangpeng.kotlintest.R
import com.liangpeng.kotlintest.basictest.IKotlinTestClass
import com.liangpeng.kotlintest.databinding.FragmentLoginBinding


class LoginFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view:View = inflater.inflate(R.layout.fragment_login,container,false)

        val binding:FragmentLoginBinding  =    DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)


        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         val iKotlinTestClass:IKotlinTestClass =   object : IKotlinTestClass {
            override fun method1(name: String): String {
                TODO("Not yet implemented")
            }
        }

    }
}