package com.liangpeng.kotlintest.basictest;

public class javaclass2 {

    TestClass testClass = new TestClass();

    String[] str = {"1","2"};
    Object[] objects = str;


    /**
     * 1.创建一个接口,接口中创建需要传递数据的方法
     * 2,在有数据的地方用接口的对象去调用方法,将数据放在参数中传递
     * 3.在需要数据的地方,创建这个接口对象,并重写它的方法,此时方法中得参数就是我们需要的数据
     */

}
