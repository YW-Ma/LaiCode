package com.study.algorithms.class08_DFS.DFSII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubListWithClear {
    public static void main(String[] args) {
        // try the subList with clear to delete certain range of elements.
        List<Integer> array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            array.add(i); // 不能用asList初始化，asList产生的array有固定的长度。
        }
        System.out.println("Before deleting first 4 elements\n array = " + array);
        array.subList(0,4).clear(); // get the "view" of [0,4] and clear them
        System.out.println("After deleting first 4 elements\n array = " + array);
    }
}
