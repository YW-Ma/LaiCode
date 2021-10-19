package com.interview.walmartlabs.leetcode;

import com.study.util.TreeNode;

public class InOrderSuccessor {
    // for a normal tree: O(N), 因为有traversal
    // node has right child --> smallest in the right child
    // node no right child --> do a normal in order to find / if is BST and has parent, then find the first parent larger than me.
    
    // for a BST:   avg O(logN)
    // from root --> if larger then go left, if smaller/equal then go right
    //      update sucessor when go left.
    //      stop when reach to null
    
    
    // do an in-order traversal, find the node whose previous is p.
    TreeNode ans;
    TreeNode previous;
    
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            TreeNode leftmost = p.right;
            while (leftmost.left != null) {
                leftmost = leftmost.left;
            }
            return leftmost;
        } else {
            inorderSuccessorHelper(root, p);
            return this.ans;
        }
    }
    
    public void inorderSuccessorHelper(TreeNode root, TreeNode p) {
        // base case
        if (root == null) {
            return;
        }
        
        // left
        inorderSuccessorHelper(root.left, p);
        
        // myself
        if (p == previous && this.ans == null) { // 由于会不断继续走下去，所以要保证只返回第一个
            this.ans = root;
            return;
        }
        this.previous = root;
        
        // right
        inorderSuccessorHelper(root.right, p);
    }
}
