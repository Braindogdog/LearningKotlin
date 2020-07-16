package com.myself.learningkotlin;

import org.jetbrains.annotations.NotNull;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/**
 * created by Liang
 * on2020/7/16
 * desc:
 */
public class javaClassTest {
    private CallBack callBack;
    private CallBack callBack2;

    public void test() {
        String name = "liangpeng";
        if(callBack!=null) {
            callBack.show(name,12);

        }
        if(callBack2!=null) {
            callBack2.show(name,12);

        }
    }


    public void setCallBack(CallBack callBack ) {
        this.callBack = callBack;
    }

    public void setCallBack2(String box,CallBack callBack) {
        this.callBack2 = callBack;
    }

    interface CallBack {
        void show(String name,int age);
    }
}
