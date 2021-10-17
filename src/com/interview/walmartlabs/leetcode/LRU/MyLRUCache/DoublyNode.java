package com.interview.walmartlabs.leetcode.LRU.MyLRUCache;

public class DoublyNode {
    int val;
    int key;
    DoublyNode prev;
    DoublyNode next;

    public DoublyNode(int key, int val) { // 需要知道key，不然不知道应该删掉哪个hashEntry
        this.key = key;
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}
