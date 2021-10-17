package com.interview.walmartlabs.leetcode.LRU.LinkedHashMap;

import java.util.*;

public class LRUCache extends LinkedHashMap<Integer, Integer> {
    int capactiy;
    public LRUCache(int capactiy) {
        super(capactiy, 0.75F, true);
        // Params:
        //  initialCapacity – the initial capacity
        //  loadFactor – the load factor
        //  accessOrder – the ordering mode - true for access-order, false for insertion-order
        // HashMap的变量顺序是不可预测的，这意味着便利的输出顺序并不一定和HashMap的插入顺序是一致的。这个特性通常会对我们的工作造成一定的困扰。为了实现这个功能，我们可以使用LinkedHashMap。
        this.capactiy = capactiy;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capactiy;
    }
}
