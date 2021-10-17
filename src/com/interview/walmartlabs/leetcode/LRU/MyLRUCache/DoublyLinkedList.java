package com.interview.walmartlabs.leetcode.LRU.MyLRUCache;

public class DoublyLinkedList {
    DoublyNode head;
    DoublyNode tail;

    public DoublyLinkedList() {
        head = new DoublyNode(0, 0);
        tail = new DoublyNode(0, 0);
        head.next = tail;
        tail.prev = head; // two dummy nodes.
    }

    public void moveToHead(DoublyNode node) {
        if (node.prev != null) {  // 也可能是直接“add to haed" 所以给一个孤立的node， 没有prev和next
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }

        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    public DoublyNode pollLRUNode() {
        if (tail.prev == head) {
            return null;
        }
        DoublyNode res = tail.prev;
        res.prev.next = tail;
        tail.prev = res.prev;

        res.next = null;
        res.prev = null; // 搞干净
        return res;
    }
}
