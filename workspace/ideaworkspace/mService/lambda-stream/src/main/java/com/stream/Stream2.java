package com.stream;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Description: 描述
 * @author: LinWeiQi
 */
public class Stream2 {
    //可以通过Collection 系列集合提供stream 或parallelStream
    List<String> list = new ArrayList<>();
    Stream<String> stream1 = list.stream();

    //可以通过数组
    Integer[] nums = new Integer[10];
    Stream<Integer> stream2 = Arrays.stream(nums);

    //可以通过Stream类中静态方法
    Stream<Integer> stream3 = Stream.of(1, 2, 3, 4, 5);

    //创建无限流
    //迭代
    //limit取前十个
    public void limit(int limit) {
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2).limit(10);
        stream4.forEach(System.out::println);
    }


    //生成随机数（两个）
    public void random(int random) {
        Stream<Double> Stream5 = Stream.generate(Math::random).limit(2);
        Stream5.forEach(System.out::println);
    }

}
