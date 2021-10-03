package com.oa.arista;
// Create a BST, and then get the inorder-predecessor and preorder-predecessor
public class BuildBSTAndGetSuccessor {
    // static node class
    // static methods
    
    static class Node {
        int val;
        Node left;
        Node right;
        
        Node(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    
    static Node insert(Node root, int val) {
        // @param val: new value
        // @return updated root
        if (root == null) {
            return new Node(val);
        }
        if (val < root.val) {
            root.left = insert(root.left, val);
        } else if (val > root.val) {
            root.right = insert(root.right, val); // 如果需要parent，就在这里用tmp接，然后把tmp.parent设置成root.
        }
        return root;
    }
    
    static Node inorderSuccessor(Node root, int val) {
        // @param val: target value
        // @return the in-order successor the node with val value
        
        // BST inorder successor: will be the node in the bottom-left corner in right-sub-tree, in BST, it's the "smallest in the right-sub-tree"
        // BST preorder successor: first find target T
        //       for normal tree:  1. T.left        2. T.right      3.go up, find an ancestor that has both left & right children, then get the right child  (or return null if arrive root)
        //       for BST:          same order as normal tree
        return null;
    }
    
    public static void main(String[] args) {
        Node root = null;
        root = insert(root, 20);
        root = insert(root, 8);
        root = insert(root, 22);
        root = insert(root, 4);
        root = insert(root, 12);
        root = insert(root, 10);
        root = insert(root, 14);
        System.out.println(root);
    }
    
    /*
    static Node search(int val, Node roo) {
        // @param val: target value
        // @return target node
    }
    
    static Node delete(int val, Node roo) {
        // @param val: target value
        // @return updated root
    }*/
    
    
}
