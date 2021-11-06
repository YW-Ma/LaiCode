//package com.interview.walmartlabs.leetcode;
//
//import com.study.util.ListNode;
//import com.study.util.TreeNode;
//
//public class SortedArrayReconstructBST_Inorder {
//    // 1. DFS简单思想：可以每次找到middle，然后建立起来。 middle的左孩子、右孩子都是返回值。相当于是post order入手
//    //                O(nlogn) 因为一共有logn层，每层开销n。
//    // 2. DFS优化：实际上可以从inorder入手构建（画图），然后构建的时候肯定从head开始（画图）
//    //                O(n)
//
//    private ListNode head;
//
//    public TreeNode sortedListToBST(ListNode head) {
//        this.head = head;
//        int size = 0;
//        ListNode cur = head;
//        while (cur != null) {
//            size++;
//            cur = cur.next;
//        }
//        return constructBST(0, size - 1);
//    }
//
//    private TreeNode constructBST(int l, int r) {
//        // base case:
//        if (l > r) {
//            return null;
//        }
//
//        int mid = l + (r - l) / 2;
//        // left
//        TreeNode left = constructBST(l, mid - 1);
//        // self
//        TreeNode root = new TreeNode(head.val);
//        head = head.next;
//        root.left = left;
//        // right
//        root.right = constructBST(mid + 1, r);
//        return root;
//    }
//}
