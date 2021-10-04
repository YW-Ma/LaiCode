package com.study.algorithms.class02_sorting_algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuickSortWithAPI {
    // Define SpecialArray:
    //	-peek() //return the first element value
    //	-size() //return the SpecialArray size
    //	-swap(int idx) //swap the first element with the idx element

    // 实现 selection sort 和 quick sort （前者是每次换到开头，记录最大的，swap到尾巴）（后者依然是挡板，但是每次swap要多几个步骤）


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list.add(10);
        list.add(1, 100);
        System.out.println(list);
        list.sort(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1.equals(o2)) {
                    return 0;
                } else if (o1 > o2) {
                    \]
                    return -1;
                }
                return 1;
            }
        });
        System.out.println(list);
    }
}
