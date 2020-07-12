package com.liangpeng.kotlintest.basictest;

import android.app.Activity;
import android.widget.Button;

import androidx.annotation.NonNull;

public class TestClass {

    protected void arrayTest() {

        String[] strs = {"1", "2", "3"};
        int[] ints = {1, 2, 3};
        String[] strs2 = new String[2];
        for (String str : strs) {

        }

        strs2[0] = "1";

    }

    ITestClass iTestClass = new ITestClass() {
        @Override
        public void method1(String name) {

        }
    };

    class InnerClass implements ITestClass {
        @Override
        public void method1(String name) {

        }
    }
}
