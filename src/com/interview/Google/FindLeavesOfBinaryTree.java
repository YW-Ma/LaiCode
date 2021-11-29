package com.interview.Google;

import com.study.util.TreeNode;

import java.util.*;

public class FindLeavesOfBinaryTree {
    List<List<Integer>> res;
    public List<List<Integer>> findLeaves(TreeNode root) {
        // list of leaf nodes
        res = new ArrayList<>();
        dfs(root);
        return res;
    }

    private int dfs(TreeNode root) {

        // base case:
        if (root == null) {
            return -1; // return the height
        }

        int height = Math.max(dfs(root.left), dfs(root.right)) + 1;
        System.out.println(res);
        // if the list for current height hasn't been created
        if (height == res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(height).add(root.val);
        return height;
    }
}
