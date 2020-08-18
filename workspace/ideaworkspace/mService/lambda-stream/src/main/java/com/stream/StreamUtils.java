package com.stream;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description: 操作数据中间态 终态
 * @author: LinWeiQi
 */
public class StreamUtils {

    /**
     * 过滤
     */
    public static void streamFilterTest(List<String> lists) { //要明确这list的泛型类型，否则jvm不能根据上下文确定参数类型
        lists.stream().filter((s -> s.startsWith("a"))).forEach(System.out::println);//将开头是a的过滤出来

        //等价于以上操作
        Predicate<String> predicate = (s) -> s.startsWith("a");//将开头是a的过滤出来
        lists.stream().filter(predicate).forEach(System.out::println);

        //连续过滤
        Predicate<String> predicate1 = (s -> s.endsWith("1"));//将开头是a，并且结尾是1的过滤出来
        lists.stream().filter(predicate).filter(predicate1).forEach(System.out::println);

    }

    /**
     * 排序
     */
    private static void streamSortedTest(List<String> list) {
        //默认排序
        list.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
        System.out.println("- - - - - - - - -");
        //自定义排序
        list.stream().sorted(((s, t1) -> t1.compareTo(s))).filter(s -> s.startsWith("a")).forEach(System.out::println);

    }

    /**
     * 自定义映射规则
     */
    private static void streamMapTest(List<String> list) {
        list.stream().map(String::toUpperCase).sorted((s, t1) -> t1.compareTo(s)).forEach(System.out::println);
        System.out.println("- - - - - - ");
        //自定义映射规则
        Function<String, String> function = s -> {
            return s + ".map3";
        };
        list.stream().map(function).forEach(System.out::println);

    }

    /**
     * 匹配
     */
    private static void streamMatchTest(List<String> list) {
        //流对象中只要有一个元素匹配就返回true
        boolean anyStartWithA = list.stream().anyMatch(s -> s.startsWith("a"));
        System.out.println("集合中是否有以'a'来头：" + anyStartWithA);
        //流对象中每一个元素都匹配才返回true
        boolean allStartWithA = list.stream().allMatch(s -> s.startsWith("a"));
        System.out.println("集合中每一个都是以'a'开头：" + allStartWithA);
        //流对象中没有匹配时返回true
        boolean noneStartWithA = list.stream().noneMatch(s -> s.startsWith("c"));
        System.out.println("集合中没有以'c'开头：" + noneStartWithA);
    }

    /**
     * 操作后生成新的集合
     *
     * @param [list]
     * @return void
     * @date 2020/1/6
     */
    private static List newCollection(List<String> list) {
        List<String> listNew = list.stream().filter(s -> s.startsWith("b")).sorted().collect(Collectors.toList());
        System.out.println(listNew);
        return listNew;
    }

    /**
     * @param [list]
     * @return void
     * @TODO 允许我们用自己的方式计算元素或者将一个stream中元素以某种规律关联
     * @date 2020/1/6
     */
    private static void Reduce(List<String> list) {
        Optional<String> optional = list.stream().sorted().reduce((s, s2) -> {
            System.out.println(s + "-" + s2);
            return s + "-" + s2;
        });
    }

    /**
     * @param [list]
     * @return void
     * @TODO 计数
     * @date 2020/1/6
     */
    private static void streamCountTest(List<String> list) {
        long count = list.stream().filter(s -> s.startsWith("b")).count();
        System.out.println("以'b'开头的数量：" + count);

    }


    /**
     * @param [list]
     * @return void
     * @TODO 并行stream 多核cpu效率高
     * @date 2020/1/6
     */
    private static void parallelStreamSortedTest(List<String> list) {
        long startTime = System.nanoTime();//返回最准确的可用系统计时器的当前值，以毫微秒为单位。
        long count = list.parallelStream().sorted().count();
        long endTime = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.printf("并行排序花费时间：%d ms", millis);
    }

    /**
     * @param [list]
     * @return void
     * @TODO 串行stream 单核效率高
     * @date 2020/1/6
     */
    private static void streamSorted(List<String> list) {
        long startTime = System.nanoTime();//返回最准确的可用系统计时器的当前值，以毫微秒为单位。
        long count = list.stream().sorted().count();
        long endTime = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.printf("串行排序花费时间：%d ms", millis);
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList(
                "aa",
                "bb",
                "cc",
                "ax"
        );
        streamFilterTest(list);
        //创建一个大集合
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            UUID uuid = UUID.randomUUID();
            list2.add(uuid.toString());
        }
    }
}
