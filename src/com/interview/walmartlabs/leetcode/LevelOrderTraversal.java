package com.interview.walmartlabs.leetcode;

import com.study.util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    // recursion:  也是O(n)
    List<List<Integer>> levels;
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        // corner case:
        if (root == null) {
            return res;
        }
        
        // BFS is better to deal with it, 但是开销也是O(n), 只是更加直观。
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
    
    public List<List<Integer>> levelOrder_recursion(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        
        levels = new ArrayList<>();
        levelOrder(root, 0);
        return levels;
    }
    
    private void levelOrder(TreeNode root, int level) {
        // 如果本层还没有list，就为本层创建list。由于是一层层深入，所以可以用size来判断
        if (levels.size() == level) {
            levels.add(new ArrayList<>());
        }
        // 把自己加进去，然后递归调用孩子们
        levels.get(level).add(root.key);
        if (root.left != null) {
            levelOrder(root.left, level + 1);
        }
        if (root.right != null) {
            levelOrder(root.right, level + 1);
        }
    }
}
