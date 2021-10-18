package com.interview.walmartlabs.leetcode.LRU.MyLRUCache;

public class Tester {
    public static void main(String[] args) {
        LRUCache myCache = new LRUCache(2);
        myCache.put(1,0); // [1]
        myCache.put(2,2); // [1,2]
        myCache.get(1); //   [2,1]   expect 1
        myCache.put(3,3); // [3,1]
        myCache.get(2); //  expect null
        myCache.put(4,4); // [4,3]
        System.out.println("finish");
    }
}
