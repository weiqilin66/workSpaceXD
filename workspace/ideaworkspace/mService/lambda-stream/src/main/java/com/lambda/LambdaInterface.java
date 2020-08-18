package com.lambda;

/**
 * @Description: Lambda表达式：可以让你的代码更加的简洁。lambda无法单独出现，需要一个函数式接口来盛放，
 * 可以说lambda表达式方法体是函数式接口的实现，lambda实例化函数式接口，可以将函数作为方法参数，或者将代码作为数据对待。
 * @author: LinWeiQi
 */
@FunctionalInterface
public interface LambdaInterface {
    int add(int value);
}
