package com.interview.walmartlabs.leetcode.LFU;

public class NodeList {
    Node head;
    Node tail;
    
    public NodeList() {
        this.head = Node.createDummyNode();
        this.tail = Node.createDummyNode();
        head.next = tail;
        tail.prev = head;
    }
    
    public void insertFirst(Node node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }
        
        this.head.next.prev = node;
        node.next = this.head.next;
        this.head.next = node;
        node.prev = this.head;
    }
    
    public void deleteNode(Node node) {
        if (node == null) {
            throw new IllegalArgumentException();
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }
    
    public boolean isEmpty() {
        return head.next == tail;
    }
    
    public Node getLastNode() {
        if (tail.prev == head) {
            throw new IllegalArgumentException();
        }
        return tail.prev;
    }
}
