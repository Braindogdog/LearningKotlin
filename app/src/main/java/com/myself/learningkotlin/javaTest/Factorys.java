package com.myself.learningkotlin.javaTest;

/**
 * created by Liang
 * on2020/7/17
 * desc:
 */
public class Factorys {


    public static void main(String[] args) {
        CallBackImpl callBack = new CallBackImpl(new ICallBack() {
            @Override
            public void dosomething() {
                System.out.println("I am doing something!");
            }
        });
        callBack.dosomething();

    }
}
