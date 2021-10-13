package com.interview.misc;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class TreeMapExercise {
    public static void main(String[] args) {
        TreeMap<Integer, int[]> treeMap = new TreeMap<>();
        TreeMap<Integer, int[]> reverseTreeMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1; // o1大的话优先（即descending）
            }
        });
        // in tree map, entries are sorted ascending to the natural ordering of its keys.
        // or by a comparator provided at map creation time.
        for (int i = 0; i < 5; i++) {
            treeMap.put(i, new int[]{1,2,3});
            reverseTreeMap.put(i, new int[]{1,2,3});
        }
        
        System.out.println(treeMap); // 0 1 2 3 4
        System.out.println(reverseTreeMap); // 4 3 2 1 0
        
        
        // 底层实现：
        // TreeMap implements的是SortedMap这个interface，SortedMap是extend了Map这个interface。
        //      TreeMap基于红黑树 (self-balanced BST) 实现的，
        //      TC: O(logN) insert，N是# of existed entry.    -->    对于需要按照key排序的场景，就请使用TreeMap。
        // HashMap 则是直接implements了Map这个interface
        
        // Speical methods: TreeMap 提供了一些特别的methods：
        /* 1  firstKey() --> return the smallest key if in ascending order
        *  2  lastKey()
        *  3  headMap(key_value) --> return the subMap with entries smaller than key_value.
        *  4  subMap(start key, end key) --> right is excluding
        *  5  higherKey(key_value) --> return the lease key higher than the given key.
        * */
        
        int smallest_key = treeMap.firstKey(); // 也可以获得firstEntry()  --> 0
        int largest_key = treeMap.lastKey();  //  --> 4
        SortedMap<Integer, int[]> smallerMap = treeMap.headMap(3); // 不再是treeMap而是sortedMap interface了
        SortedMap<Integer, int[]> subMap = treeMap.subMap(2,5); // 2 3 4
        int higher_key = treeMap.higherKey(3); // --> 4
        System.out.println("Tested");
        
    }
}
