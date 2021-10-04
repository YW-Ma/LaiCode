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
        
        // BST inorder successor:
        //       for the node has right child: will be the node in the bottom-left corner in right-sub-tree, in BST, it's the "smallest in the right-sub-tree"
        //       for the node don't have right child: the one in the ancestor that has unvisited right child.(we traversed it left, go up from left)
        //                         1. (min) in T.right       2. go up, find an ancestor that has both left and right children, and the left one has been traversed.
        //                                                      有parent的情况： （while parent != null)的if分支  我<parent, 返回parent     也可以理解成 只要我>parent，即我是右孩子，那么就继续找parent。直到找到从左孩子上来的。

        //                                                      无parent的情况：从上往下找，从root出发，每次用自己的data和target相比，如果tar在左边，那么root左走【并且登记原root为候选】，tar在右边，root右走【但是不登记】。 直到走到tar，返回最后一个候选即可。
                                                                // 原理：只有tar在左子树的node，是在后面的（因为BST inorder的特性），才可能成为successor。




        // BST preorder successor: first find target T
        //       for normal tree:  1. T.left        2. T.right      3.go up, find an ancestor that has both left & right children, then get the right child  (or return null if arrive root)
        //       for BST:          same order as normal tree                 有parent的情况： （while parent != null)的if分支   我<parent && parent有right, 那么返回我。
        //       "如何知道我是左孩子还是右孩子" --> 在BST中通过检查value就可以确定，如果我比我parent大，那我是右孩子。
        //
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
