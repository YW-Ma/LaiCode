package com.interview.walmartlabs;

import java.util.*;

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
    Map<Node, Node> visited = new HashMap<>();

    // 这个LinkedList有一个额外的random指针，指向某个未来的位置。
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }


        Node newHead = new Node(head.val);
        Node res = newHead;
        visited.put(head, newHead); // 一定注意把第一个也加进去。
        while (head != null) {
            // get the cloned node of next and random
            newHead.next = getClone(head.next);
            newHead.random = getClone(head.random);

            head = head.next;
            newHead = newHead.next;
        }
        return res;
    }

    private Node getClone(Node node) {
        if (node == null) {
            return null;
        }
        if (!visited.containsKey(node)) {
            visited.put(node, new Node(node.val));
        }
        return visited.get(node);
    }
}
