package com.interview.walmartlabs.leetcode.LFU;

import java.util.HashMap;

public class LFUCache {
    // 需要一个 key -> node 的映射
    // 需要一个 freq -> node list head 的映射
    HashMap<Integer, Node> keyMap;        // Insert or Update node when [#put]. Remove node when exceed capacity.
    HashMap<Integer, NodeList> freqMap;   // When frequency changed, modify via [#addFreqMapNode] and [#deleteFreqMapNode]; When exceed capacity, find where to delete by `freqMap.get(minFreq)`
    int capacity; 
    int minFreq;   // Set to 1 if put a new key (with freq 1); Bump up 1 when revisited (either put or get)
    
    public LFUCache(int capacity) {
        this.keyMap = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
        this.minFreq = 0;
    }
    
    
    public int get(int key) {
        // get the node
        if (!keyMap.containsKey(key)) {
            return -1;
        }
        Node node = keyMap.get(key);
        // put the updated node to new location
        deleteFreqMapNode(node);
        node.freq++;
        addFreqMapNode(node);
        if (!freqMap.containsKey(minFreq)) {
            minFreq++;
        }
        // return val;
        return node.val;
    }
    
    
    public void put(int key, int val) {
        // if not exist
        if (!keyMap.containsKey(key)) {
            // if reach the capacity
            if (capacity == 0) {
                return;
            }
            // LFU 与 LRU的核心区别是淘汰策略(即下方的if block)不同. 为此需要维护 minFreq 和 freqMap.
            if (keyMap.size() == capacity) { // delete LFU node
                NodeList list = freqMap.get(minFreq);
                Node node = list.getLastNode();
                deleteFreqMapNode(node);
                keyMap.remove(node.key);
            }
            Node node = new Node(key, val, 1);
            keyMap.put(key, node);
            addFreqMapNode(node);
            minFreq = 1;
        }
        // if exist
        else {
            Node node = keyMap.get(key);
            node.val = val;
            // update
            deleteFreqMapNode(node);
            node.freq++;
            addFreqMapNode(node);
            if (!freqMap.containsKey(minFreq)) {
                minFreq++;
            }
        }
    }
    
    public void addFreqMapNode(Node node) {
        if (freqMap.containsKey(node.freq)) { // 包含freq，就插入
            freqMap.get(node.freq).insertFirst(node);
        } else {
            NodeList list = new NodeList(); // 不包含freq就新建
            list.insertFirst(node);
            freqMap.put(node.freq, list);
        }
    }
    
    public void deleteFreqMapNode(Node node) {
        freqMap.get(node.freq).deleteNode(node);
        if (freqMap.get(node.freq).isEmpty()) { // empty list就删掉
            freqMap.remove(node.freq);
        }
    }
    
}
