package com.interview.walmartlabs.leetcode;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class ReverseNodeInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode end = dummy;
        // 开始状态，pre,end指向下一组k个元素的前面的那个元素
        while (end.next != null) {
            // end: 对于凑不满k个的结尾，直接break
            for (int i = 0; i < k; i++) {
                end = end.next;
                if (end == null) {
                    break;
                }
            }
            if (end == null) {
                break;
            }
            // normal:
            ListNode start = pre.next;
            ListNode nextStart = end.next;
            pre.next = null;
            end.next = null;
            reverse(start);
            pre.next = end;
            start.next = nextStart;

            // move:
            pre = start;
            end = start;
        }
        return dummy.next;
    }

    private void reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    /*
    步骤分解:

链表分区为已翻转部分+待翻转部分+未翻转部分
每次翻转前，要确定翻转链表的范围，这个必须通过 k 此循环来确定
需记录翻转链表前驱和后继，方便翻转完成后把已翻转部分和未翻转部分连接起来
初始需要两个变量 pre 和 end，pre 代表待翻转链表的前驱，end 代表待翻转链表的末尾
经过k此循环，end 到达末尾，记录待翻转链表的后继 next = end.next
翻转链表，然后将三部分链表连接起来，然后重置 pre 和 end 指针，然后进入下一次循环
特殊情况，当翻转部分长度不足 k 时，在定位 end 完成后，end==null，已经到达末尾，说明题目已完成，直接返回即可
时间复杂度为 O(n*K)O(n∗K) 最好的情况为 O(n)O(n) 最差的情况未 O(n^2)O(n
2
 )
空间复杂度为 O(1)O(1) 除了几个必须的节点指针外，我们并没有占用其他空间

作者：reals
链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/solution/tu-jie-kge-yi-zu-fan-zhuan-lian-biao-by-user7208t/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    * */
}
