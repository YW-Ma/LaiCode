package com.study.algorithms.class19_cross_training_2.DeepCopy;

import java.util.HashMap;
import java.util.Map;

class RandomListNode {
    public int value;
    public RandomListNode next;
    public RandomListNode random;

    public RandomListNode(int value) {
        this.value = value;
    }
}

// cur = head
// 每次检查cur是不是null，不是的话就检查 cur.next和cur.random是不是已经copy了（合并stage1和stage2）
// 但是移动cur的时候，只是向next移动 （相当于stage1）

public class DeepCopyLinkedList {
    public RandomListNode copy(RandomListNode head) {
        if (head == null) {
            return null;
        }

        Map<RandomListNode, RandomListNode> oldToNew = new HashMap<>();
        RandomListNode newHead = new RandomListNode(head.value); // need not null head.
        RandomListNode cur = head;
        RandomListNode newCur = newHead;
        oldToNew.put(head, newHead);

        while (cur != null) {
            // get the reference of copied nodes
            if (cur.next != null) {
                if (!oldToNew.containsKey(cur.next)) { // [2] what we need to check before: existed or not
                    oldToNew.put(cur.next, new RandomListNode(cur.next.value));
                }
                newCur.next = oldToNew.get(cur.next); // [1] what we want: build the relationship
            }
            if (cur.random != null) {
                if (!oldToNew.containsKey(cur.random)) {
                    oldToNew.put(cur.random, new RandomListNode(cur.random.value));
                }
                newCur.random = oldToNew.get(cur.random);
            }

            // move forward:
            newCur = newCur.next;
            cur = cur.next;
        }

        return newHead;
    }

    // O(n)
}
