package com.interview.walmartlabs.leetcode.LFU;

import java.util.HashMap;

public class LFUCache {
    // 需要一个 key -> node 的映射
    // 需要一个 freq -> node list head 的映射
    
    HashMap<Integer, Node> keyMap;
    HashMap<Integer, NodeList> freqMap;
    int capacity;
    int minFreq;
    
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
        addFreqMap(node);
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
            if (keyMap.size() == capacity) { // delete LFU node
                NodeList list = freqMap.get(minFreq);
                Node node = list.getLastNode();
                deleteFreqMapNode(node);
                keyMap.remove(node.key);
            }
            Node node = new Node(key, val, 1);
            keyMap.put(key, node);
            addFreqMap(node);
            minFreq = 1;
        }
        // if exist
        else {
            Node node = keyMap.get(key);
            node.val = val;
            // update
            deleteFreqMapNode(node);
            node.freq++;
            addFreqMap(node);
            if (!freqMap.containsKey(minFreq)) {
                minFreq++;
            }
        }
    }
    
    public void addFreqMap(Node node) {
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
        if (freqMap.get(node.freq).isEmpty()) {
            freqMap.remove(node.freq);
        }
    }
    
}
