package com.interview.walmartlabs;

import java.util.*;
import java.util.stream.Collectors;

public class JavaStreamAPI {
    // Stream是Java8的两点，也借助于lambda表达式

    // 可以用于聚合操作，大批量数据操作。
    // 可以认为是简单的filter - map - reduce过程

    /*
    * Intermediate 操作 (map, filter)
    　map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered

    Terminal 操作 (collect, reduce)
    　forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
                                                  .collect(Collectors.toList())
    Short-circuiting 操作
    　anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit

    * */


    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1,2,3,4);
        List<Integer> squares = nums.stream().map(n -> n * n).collect(Collectors.toList());

        nums.stream().filter(n -> n % 2 == 0).forEach(n -> System.out.println(n));

        List<List<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Integer> news = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                news.add(j);
            }
            lists.add(news);
        }

        List<Integer> flatten = lists.stream().flatMap(child -> child.stream()).collect(Collectors.toList());
        System.out.println(flatten);
    }
}
