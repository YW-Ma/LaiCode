package com.study.algorithms.final2;

import com.study.util.TreeNode;

public class Distance {
    class Resp {
        TreeNode node;
        int depth;
        int distance;
    
        public Resp(TreeNode n, int i) {
            this.node = n;
            this.depth = i;
        }
    }
    
    public int getDistance(TreeNode root, TreeNode a, TreeNode b) { // assume a and b in tree and not null;
        Resp resp = helper(root, a, b);
        return resp.distance;
    }
    
    private Resp helper(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) {
            return new Resp(null, 0);
        }
        if (root == a || root == b) {
            return new Resp(a, 0);
        }
        
        Resp left = helper(root.left, a, b);
        Resp right = helper(root.right, a, b);
        
        if (left.node == null && right.node == null) {
            return new Resp(null, 0);
        }
        if (left.node == null) {
            right.depth++;
            return right;
        }
        if (right.node == null) {
            left.depth++;
            return left;
        }
        
        Resp res = new Resp(root, left.depth + 1);
        res.distance = left.depth + right.depth + 2;
        return res;
    }
}
