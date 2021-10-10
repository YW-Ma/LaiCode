package com.interview.walmartlabs;

public class CopyListWithRandomPointer {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    // 这个LinkedList有一个额外的random指针，指向某个未来的位置。
    public Node copyRandomList(Node head) {

    }
}
