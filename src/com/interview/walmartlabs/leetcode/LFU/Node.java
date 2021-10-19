package com.interview.walmartlabs.leetcode.LFU;

public class Node {
    // I just use public field to save time, in real programming I will use private field + public getter/setter
    int key;
    int val;
    int freq;
    Node prev;
    Node next;
    
    public Node(int key, int val, int freq, Node prev, Node next) {
        this.key = key;
        this.val = val;
        this.freq = freq;
        this.prev = prev;
        this.next = next;
    }
    
    public Node(int key, int val, int freq) {
        this.key = key;
        this.val = val;
        this.freq = freq;
    }
    
    public static Node createDummyNode() {
        return new Node(-1, -1, -1, null, null);
    }
}
