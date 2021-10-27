//package com.interview.walmartlabs.leetcode;
//
//import com.study.util.ListNode;
//import com.study.util.TreeNode;
//
//public class SortedArrayReconstructBST_naiveDFS {
//    // 1. DFS简单思想：可以每次找到middle，然后建立起来。 middle的左孩子、右孩子都是返回值。相当于是post order入手
//    //                O(nlogn) 因为一共有logn层，每层开销n。
//    //               具体来说，为了能复用同一个getMiddle，会在找到middle以后把list拆分成两个sub list。
//
//    // 2. DFS优化：实际上可以从inorder入手构建（画图），然后构建的时候肯定从head开始（画图）
//    //                O(n)
//
//    private ListNode cutInMiddle(ListNode head) {
//        // 1 2 3 4          -->   12  + 34
//        //     s
//        //         f  (f == null)
//        // 1 2 3 4 5        -->   12  + 345    记录一个prev，就可以断开了。
//        //     s            s is a right-biased middle.
//        //         f  (f.next == null)
//        ListNode prev = null;
//        ListNode slow = head;
//        ListNode fast = head;
//        while (fast != null && fast.next != null) {
//            prev = slow;
//            fast = fast.next.next;
//            slow = slow.next;
//        }
//        if (prev != null) {
//            prev.next = null;
//        }
//        return slow;
//    }
//
//    public TreeNode sortedListToBST(ListNode head) {
//        // base case: head is an isolated node or empty
//        if (head == null) {
//            return null;
//        }
//        if (head.next == null) {
//            return new TreeNode(head.val);
//        }
//
//
//        // create root
//        ListNode mid = cutInMiddle(head); // head and mid is the head of two sub lists.
//        TreeNode root = new TreeNode(mid.val);
//        // connect to left --> using head
//        root.left = sortedListToBST(head);
//        // connect to right --> using mid.next
//        root.right = sortedListToBST(mid.next);
//        return root;
//    }
//}
