package com.interview.walmartlabs.leetcode;

import com.study.util.TreeNode;

import java.util.*;

public class TwoSumInBST {
    public boolean findTarget(TreeNode root, int k) {
        // BFS + HashSet
        // notice:
        HashSet<Integer> set = new HashSet<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (set.contains(k - cur.key)) {
                return true;
            }
            set.add(cur.key); // 注意在check完某个位置后才加入hashset、hashmap
            if (cur.left != null) {
                q.offer(cur.left);
            }
            if (cur.right != null) {
                q.offer(cur.right);
            }
        }
        return false;
    }
}
