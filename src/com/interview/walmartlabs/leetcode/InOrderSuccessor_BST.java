package com.interview.walmartlabs.leetcode;

import com.study.util.TreeNode;

public class InOrderSuccessor_BST {
    // for a normal tree: O(N), 因为有traversal
    // node has right child --> smallest in the right child
    // node no right child --> do a normal in order to find / if is BST and has parent, then find the first parent larger than me.
    
    // for a BST:   avg O(logN)
    // from root --> if larger then go left, if smaller/equal then go right
    //      update sucessor when go left.
    //      stop when reach to null
    
    
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode successor = null;
        while (root != null) {
            if (root.key <= p.key) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }
}
