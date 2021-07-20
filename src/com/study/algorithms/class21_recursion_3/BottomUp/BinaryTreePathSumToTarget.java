package com.study.algorithms.class21_recursion_3.BottomUp;

import com.study.util.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePathSumToTarget {
    // https://app.laicode.io/app/problem/141
    
    // Basic Solution [top-down pass prefix]
    public boolean exist(TreeNode root, int target) {
        if (root == null) {
            return false;
        }
        List<Integer> path = new ArrayList<>();
        return helper(root, path, target);
    }
    // 注意：helper和helper2的含义一样，更好理解的DFS是helper2，更美观的是hleper。
    private boolean helper(TreeNode root, List<Integer> path, int target) {
        // Base case: check all possible subpath ended at newly added node:
        path.add(root.key);
        int sum = 0;
        for (int i = path.size() - 1; i >= 0; i--) { // 不能从0开始到size-1， 那样漏掉大部分可能
            sum += path.get(i);
            if (sum == target) {
                return true;
            }
        }
        
        if (root.left != null && helper(root.left, path, target)) {
            return true;
        }
        if (root.right != null && helper(root.right, path, target)) {
            return true;
        }
        
        path.remove(path.size() - 1);
        return false;
    }
    
    
    // 但是如果想使用helper2，需要先在path里把root加入。即在caller里预先加入最初的root。 否则漏掉 target就等于root的解。
    private boolean helper2(TreeNode root, List<Integer> path, int target) {
        // Base case: check all possible subpath ended at newly added node:
        // 可以很明显看出来，是DFS，两个Branch即尝试两个孩子。 每个layer在进行从当前root开始的supath的检查
        int sum = 0;
        for (int i = path.size() - 1; i >= 0; i--) { // 不能从0开始到size-1， 那样漏掉大部分可能
            sum += path.get(i);
            if (sum == target) {
                return true;
            }
        }
        
        if (root.left != null) {
            path.add(root.left.key);
            if (helper(root.left, path, target)) {
                return true;
            }
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.right.key);
            if (helper(root.right, path, target)) {
                return true;
            }
            path.remove(path.size() - 1);
        }
        return false;
    }
    
    
    // O(n) Solution
    // Record prefix (path)
    // Find if there is a subarray sum to target value
    
    
}
