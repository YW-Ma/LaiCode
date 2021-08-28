package com.study.algorithms.class06_Binary_Search_Tree;

import com.study.util.TreeNode;

public class Insert {
    /* if already exists, not necessary to do anything */

    // Recursion - 1 (add a node, pure function) (need a helper to remember parent)
    // 【review】 新的base case，即 cur.key == key 是可以成立于recursion和iteration的。
    //           相比于 while (cur != null) ，while (cur.key != key) 可以让recursion代码更简洁。
    public TreeNode insert1(TreeNode root, int key) {
        // corner case:
        if (root == null) {
            return new TreeNode(key);
        }
        insertKey(root, key);
        return root;
    }

    private void insertKey(TreeNode root, int key) { // tail recursion
        // can be a base case
        if (root.key == key) {
            return;
        }
        if (root.key > key) {
            if (root.left == null) {
                root.left = new TreeNode(key);
                // if not return, it's okey. since now  root.left.key == key.
            }
            insertKey(root.left, key);
        } else if (root.key < key) {
            if (root.right == null) {
                root.right = new TreeNode(key);
            }
            insertKey(root.right, key);
        }
    }
/*
    private void insertKey(TreeNode root, int key) { // tail recursion
        // can be a base case
        if (root.key == key) {
            return;
        }
        if (root.key > key) {
            if (root.left == null) {
                root.left = new TreeNode(key);
                return; // if not return, it's okey. since now  root.left.key == key.
            }
            insertKey(root.left, key);
        } else if (root.key < key) {
            if (root.right == null) {
                root.right = new TreeNode(key);
                return;
            }
            insertKey(root.right, key);
        }
    }*/

    // Recursion - 2 (return the inserted node, update the parent) - not tail
    public TreeNode insert(TreeNode root, int key) {
        // base case:
        if (root == null) {
            return new TreeNode(key);
        }
        // recursion rule:
        if (root.key > key) {
            root.left = insert(root.left, key);
        } else if (root.key < key) {
            root.right = insert(root.right, key);
        }
        return root; // including root.key == key (don't need to do anything)
    }

    // Iteration - 3 (change the solution 1 (which is a tail recursion)
    public TreeNode insert3(TreeNode root, int key) {
        // corner case:
        if (root == null) {
            return new TreeNode(key);
        }

        TreeNode cur = root;
        while (cur.key != key) {
            if (cur.key > key) {
                if (cur.left == null) {
                    cur.left = new TreeNode(key);
                }
                cur = cur.left;
            }
            if (cur.key < key) {
                if (cur.right == null) {
                    cur.right = new TreeNode(key);
                }
                cur = cur.right;
            }
        }
        return root;
    }
}
