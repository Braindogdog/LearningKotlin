package com.myself.learningkotlin.javaTest;

/**
 * created by Liang
 * on2020/7/17
 * desc:
 */
public class CallBackImpl implements ICallBack {


    private final ICallBack icallBack;

    public CallBackImpl(ICallBack icallBack) {
        this.icallBack = icallBack;
    }

    @Override
    public void dosomething() {
        System.out.println("-----before");
        icallBack.dosomething();
        System.out.println("-------after");
    }
}
