package com.interview.bloomberg;

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};

public class FlattenALinkedListWithChild {
    public Node flatten(Node head) {
        // 拍平 --> next/prev/child
        // 拍平方法是把child的内容，插入到自己和next之间。
        
        if (head == null) {
            return null;
        }
        helper(head);
        return head;
    }
    
    private Node helper(Node head) {
        // 假设helper可以把以head为头的list拍平，并返回tail。
        // 那么对于我来说，我要对每个child非null的调用这个，并且把返回的tail接在next前面
        // cur->next
        // cur->child->...->tail->next
        
        // 输入不能是null，否则我只能返回null，然后造成上一层调用的NPE
        // base case是只有一个node，可以合并到下方
        Node cur = head;
        Node prev = cur;
        while (cur != null) {
            Node next = cur.next;
            if (cur.child != null) {
                Node subHead = cur.child;
                Node subTail = helper(cur.child);
                
                cur.next = subHead;
                cur.child = null;
                subHead.prev = cur;
                if (next != null) {
                    next.prev = subTail; // 如果只有一个node，那么next是null
                    subTail.next = next;
                }
            }
            prev = cur;
            cur = cur.next;
        }
        // return current tail --> prev
        return prev;
    }
}
