package com.interview.walmartlabs.leetcode;

import com.study.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case:
        if (root == null) {
            return res;
        }
        
        // BFS is better to deal with it
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int size = q.size();
        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.key);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
            size = q.size();
            res.add(level);
        }
        return res;
    }
}
