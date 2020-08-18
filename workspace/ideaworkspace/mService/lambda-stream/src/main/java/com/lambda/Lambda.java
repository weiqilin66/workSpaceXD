package com.lambda;

import java.util.ArrayList;

/**
 * @Description: 在Lambda表达式使用中，Lambda表达式外面的局部变量会被JVM隐式的编译成final类型，Lambda表达式内部只能访问，不能修改 
 * Lambda表达式内部对静态变量和成员变量是可读可写的 
 * Lambda不能访问函数接口的默认方法，在函数接口中可以添加default关键字定义默认的方法
 * @author: LinWeiQi
 */
public class Lambda {

    public static void main(String[] args) {
        int x = 1; // 局部变量对于Lambda表达式来说是静态的 无法修改只能访问
        LambdaInterface lambdaInterface = (a) -> a * 2;// 主要应用1: 函数式接口的实现
        LambdaInterface2 lambdaInterface2 = (name) -> System.out.println(name);
        //主要应用2 :回调函数(让函数可以作为一个参数传入另一个函数中)
        new ArrayList<String>().stream().filter((s -> s.startsWith("a"))).forEach(System.out::println);
        //Lambda表达式方法引用
        int res = lambdaInterface.add(11);
        lambdaInterface2.method1("wayne");
        //实例方法引用
        Obj obj = new Obj();
        Transform<String, Integer> transform2 = obj::strToInt;
        int result2 = transform2.transform("200");
        //静态方法引用
        Transform<String, Integer> transform3 = Lambda::strToInt;
        int result3 = transform2.transform("200");
        //构造方法引用
        //  Factory<Boy> boyFactory = Boy::new;


    }

    static int strToInt(String str) {
        return Integer.valueOf(str);
    }

    /**
     * 函数式接口
     */
    @FunctionalInterface
    interface Sum {
        int add(int value);

    }

    interface Transform<A, B> {
        B transform(A a);
    }

    /**
     * 实例对象类
     */
    static class Obj {
        public int strToInt(String str) {
            return Integer.valueOf(str);
        }


    }
}