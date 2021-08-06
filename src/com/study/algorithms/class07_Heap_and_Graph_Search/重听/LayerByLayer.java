package com.study.algorithms.class07_Heap_and_Graph_Search.重听;

import java.util.*;

public class LayerByLayer {
    // suppose: Binary Tree input, List of List output (* List<List<Integer>>)
    // class TreeNode {int key; TreeNode left; TreeNode right; public TreeNode(int key){this.key = key;}}

    class TreeNode {
        int key;
        TreeNode left;
        TreeNode right;
        public TreeNode(int key) {
            this.key = key;
        }
    }

    public List<List<Integer>> layerByLayer(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        // Breath first search the tree
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> curLevel = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // expand and generate in batches
                TreeNode cur = queue.poll();
                curLevel.add(cur.key);

                if (cur.left != null) {
                    queue.offer(cur.left);  // null不能加入，就算能加入也不让加（否则cur.key会NPE）
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            res.add(curLevel);
        }
        return res;
    }

    // Time: O(n)
    // Space: O(n)
}
