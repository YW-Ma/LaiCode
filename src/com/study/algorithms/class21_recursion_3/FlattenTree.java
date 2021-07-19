package com.study.algorithms.class21_recursion_3;

import com.study.util.TreeNode;

public class FlattenTree {
    // https://app.laicode.io/app/problem/523
    // What I expect:
    //      l_head, l_tail & r_head, r_tail
    // What I do:
    //      root.right = l_head;
    //      l_tail = r_head;
    //      root.left = null;
    // what I report:
    //      new_head(root), new_tail(r_tail)
    
    class Resp {
        TreeNode newHead;
        TreeNode newTail;
        
        Resp(TreeNode head, TreeNode tail) {
            this.newHead = head;
            this.newTail = tail;
        }
    }
    
    public TreeNode flatten(TreeNode root) {
        if (root == null) {
            return null;
        }
        Resp res = helper(root);
        return res.newHead;
    }
    
    private Resp helper(TreeNode root) {
        if (root == null) {
            return null;
        }
        // What I ask:
        Resp left = helper(root.left);
        Resp right = helper(root.right);
        // What I do and report -- consider corner cases
        if (left == null && right == null) {
            return new Resp(root, root);
        }
        if (left == null) {
            root.right = right.newHead;
            root.left = null;
            return new Resp(root, right.newTail);
        }
        if (right == null) {
            root.right = left.newHead;
            root.left = null;
            return new Resp(root, left.newTail);
        }
        root.right = left.newHead;
        left.newTail.right = right.newHead;
        root.left = null;
        return new Resp(root, right.newTail);
    }
}
