package com.study.algorithms.class07_Heap_and_Graph_Search.重听;

import java.util.*;

public class CheckCompleted {
    static class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int key) {
            this.key = key;
        }
    }
    // return true if root is binary tree is null
    public boolean isCompleted(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        boolean afterNull = false; // if meet a null, set up this flag.
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            if (cur.left == null) {
                afterNull = true;
            } else if (afterNull == true) {
                return false;
            } else {
                queue.offer(cur.left); // 把正常分支放在最后一个地方。
            }

            if (cur.right == null) {
                afterNull = true;
            } else if (afterNull == true) {
                return false;
            } else {
                queue.offer(cur.right); // 把正常分支放在最后一个地方。
            }
        }

        return true;
    }
}
