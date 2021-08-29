package com.study.algorithms.class06_Binary_Search_Tree;

import com.study.util.TreeNode;

public class Delete {
    // TC: O(height)
    public TreeNode deleteTree(TreeNode root, int val) { // return the updated root.
        // corner case:
        if (root == null) {
            return null;
        }

        if (root.key > val) {
            root.left = deleteTree(root.left, val);
        } else if (root.key < val) {
            root.right = deleteTree(root.right, val);
        } else {
            if (root.left == null && root.right == null) {
                return null; // delete this node
            } else if (root.right != null) { // right exists, prefer the smallest in right
                TreeNode smallest = pickSmallest(root.right, root);
                smallest.right = root.right;
                smallest.left = root.left;
                return smallest;
            } else { // 其实这个没有必要这么复杂，走到这里说明right == null. 那么直接把左边顶上来即可。
                // TreeNode largest = pickLargest(root.left);
                // largest.left = removeLargest(root.left);
                // return largest;
                return root.left;
            }
        }
        return root;
    }

    private TreeNode pickSmallest(TreeNode cur, TreeNode prv) { // 注意要把smallest拽下来。即需要考虑 smallest.right != null的情况。
        while (cur.left != null) {
            prv = cur;
            cur = cur.left;
        }
        // now cur.left == null, but cur.right can be not null
        if (cur.right == null) {
            if (prv.left == cur) {
                prv.left = null;
            } else {
                prv.right = null;
            }
            return cur;
        }

        // if cur.right is not null
        if (prv.left == cur) {
            prv.left = cur.right;
        } else {
            prv.right = cur.right;
        }
        return cur;
    }
}
