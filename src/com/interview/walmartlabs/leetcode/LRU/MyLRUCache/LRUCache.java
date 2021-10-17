package com.interview.walmartlabs.leetcode.LRU.MyLRUCache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    DoublyLinkedList list;
    Map<Integer, DoublyNode> cache;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.list = new DoublyLinkedList();
        this.cache = new HashMap<>();
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            DoublyNode node = cache.get(key);
            list.moveToHead(node);
            return node.val;
        }
        return -1;
    }

    public void put(int key, int value) {
        // 特殊条件，如果key已有，则更新value
        if (cache.containsKey(key)) {
            DoublyNode node = cache.get(key);
            node.val = value;
            list.moveToHead(node);
            return;
        }

        // build new node and add it into doubly linked list (move to head)
        DoublyNode newNode = new DoublyNode(key, value);
        list.moveToHead(newNode);
        cache.put(key, newNode);

        // maintain capacity
        if (cache.size() > capacity) {
            DoublyNode oldNode = list.pollLRUNode();
            cache.remove(oldNode.key);
        }
    }
}
